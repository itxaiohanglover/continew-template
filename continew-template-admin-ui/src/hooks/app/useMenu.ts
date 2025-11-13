import { ref } from 'vue'
import type { TreeNodeData } from '@arco-design/web-vue'
import { listMenuDictTree } from '@/apis/system'
// 多租户下线：移除租户套餐菜单 API

/** 菜单模块 */
export function useMenu(options?: { onSuccess?: () => void }) {
  const loading = ref(false)
  const menuList = ref<TreeNodeData[]>([])

  const getMenuList = async (name?: string) => {
    try {
      loading.value = true
      const res = await listMenuDictTree({ description: name })
      menuList.value = res.data
      options?.onSuccess && options.onSuccess()
    } finally {
      loading.value = false
    }
  }

  return { menuList, getMenuList, loading }
}
