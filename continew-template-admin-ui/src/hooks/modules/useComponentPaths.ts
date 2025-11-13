import { onMounted, ref } from 'vue'
import { Message } from '@arco-design/web-vue'

interface ComponentOption {
  label: string
  value: string
}

export const useComponentPaths = () => {
  const componentOptions = ref<ComponentOption[]>([])

  const loadComponentPaths = async () => {
    try {
      const modules = import.meta.glob('@/views/**/index.vue')
      const paths = Object.keys(modules)
      componentOptions.value = paths.map((path) => {
        // 格式转化
        path = path.replace('/src/views/', '')
        const label = `@view/${path}`
        const value = path.split('.vue')[0]
        return { label, value }
      })
    } catch (error) {
      Message.error('加载组件路径失败')
      console.error('加载组件路径失败:', error)
    }
  }

  onMounted(async () => {
    await loadComponentPaths()
  })

  return {
    componentOptions,
  }
}
