<template>
  <a-spin :loading="loading" style="width: 100%">
    <a-card class="general-card" title="地理位置">
      <template #extra>
        <a-radio-group type="button" size="small">
          <a-radio value="china">中国</a-radio>
          <a-radio value="world" disabled title="暂未开放">世界</a-radio>
        </a-radio-group>
      </template>
      <div class="content">
        <div class="mapChart">
          <Chart ref="chartRef" :option="chartOption" style="height: 468px" />
        </div>
        <div class="dataShow">
          <div v-for="item in topData" :key="item.name" class="dataItem">
            <div class="title">
              <span>{{ item.name }}</span>
              <span>{{ item.value }}</span>
            </div>
            <a-progress color="#165dff" size="medium" :percent="item.value / totalValue" :show-text="false" />
          </div>
        </div>
      </div>
    </a-card>
  </a-spin>
</template>

<script setup lang="ts">
import type { EChartsOption } from 'echarts'
import { getAnalysisGeo as getData } from '@/apis/common/dashboard'
import { useChart } from '@/hooks'

const chartRef = useTemplateRef('chartRef')
const chartData = ref([])
const totalValue = ref(0)
const topData = ref([])

const { chartOption } = useChart((isDark: EChartsOption) => {
  return {
    visualMap: {
      left: 'left',
      min: 0,
      max: 20000,
      inRange: {
        color: ['#EAF4FF', '#3C7EFF'],
      },
      orient: 'horizontal',
      calculable: false,
    },
    tooltip: {
      trigger: 'item',
      formatter(data: any) {
        return `${data.name}<br/>访问次数: ${data.value || 0}`
      },
    },
    series: [
      {
        type: 'map',
        map: 'china',
        zoom: 1.1,
        label: {
          show: false,
        },
        itemStyle: {
          normal: {
            areaColor: isDark ? '#313132' : '#F6F6F6',
          },
        },
        data: chartData.value,
      },
    ],
  }
})

const loading = ref(false)
// 查询图表数据
const getChartData = async () => {
  try {
    loading.value = true
    const { data } = await getData()
    chartData.value = data.filter((item) => item.value !== 0)
    totalValue.value = data.reduce((sum, item) => sum + item.value, 0)
    topData.value = data.sort((a, b) => b.value - a.value).slice(0, 7)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  getChartData()
})
</script>

<style scoped lang="scss">
.content {
  display: flex;
  align-items: center;

  .mapChart {
    flex: 3;
  }

  .dataShow {
    height: 100%;
    max-height: 500px;
    flex: 1;
    padding: 16px 24px;
    border-radius: 4px;
    overflow: auto;
    background-color: var(--color-bg-4);

    .dataItem {
      padding-top: 16px;

      .title {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }

    .dataItem:first-child {
      padding-top: 0;
    }

    &::-webkit-scrollbar {
      display: none;
      /* 隐藏滚动条 */
    }
  }
}

.mobile {
  .dataShow {
    display: none;
  }
}
</style>
