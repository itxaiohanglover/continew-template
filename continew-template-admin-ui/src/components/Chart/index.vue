<template>
  <VCharts ref="chart" :option="option" :autoresize="autoResize" :style="{ width, height }" />
</template>

<script setup lang="ts">
import { registerMap } from 'echarts/core'
import VCharts from 'vue-echarts'
import worldMap from './world.json'
import chinaMap from './china.json'

defineProps({
  option: {
    type: Object,
    default() {
      return {}
    },
  },
  autoResize: {
    type: Boolean,
    default: true,
  },
  width: {
    type: String,
    default: '100%',
  },
  height: {
    type: String,
    default: '100%',
  },
})

registerMap('world', worldMap)
registerMap('china', chinaMap)

const chart = ref(null)
defineExpose({
  chart,
})
</script>

<style scoped lang="less"></style>
