<template>
  <a-drawer v-model:visible="visible" title="租户详情" :width="width >= 600 ? 600 : '100%'" :footer="false">
    <a-descriptions :column="2" size="large" class="general-description">
      <a-descriptions-item label="ID">
        <a-typography-paragraph copyable>{{ dataDetail?.id }}</a-typography-paragraph>
      </a-descriptions-item>
      <a-descriptions-item label="编码">
        <a-typography-paragraph copyable>{{ dataDetail?.code }}</a-typography-paragraph>
      </a-descriptions-item>
      <a-descriptions-item label="名称">{{ dataDetail?.name }}</a-descriptions-item>
      <a-descriptions-item label="套餐">{{ dataDetail?.packageName }}</a-descriptions-item>
      <a-descriptions-item label="域名">
        <a v-if="dataDetail?.domain" style="color: rgb(var(--arcoblue-7))">{{ dataDetail?.domain }}</a>
        <span v-else style="color: red" class="text-red-4">未设置</span>
      </a-descriptions-item>
      <a-descriptions-item label="过期时间">
        <span v-if="!dataDetail?.expireTime">
          <span>永不过期</span>
        </span>
        <span v-else>{{ dataDetail?.expireTime }}</span>
      </a-descriptions-item>
      <a-descriptions-item label="管理员用户">{{ dataDetail?.adminUsername }}</a-descriptions-item>
      <a-descriptions-item label="状态">
        <a-tag v-if="dataDetail?.status === 1" color="green">启用</a-tag>
        <a-tag v-else color="red">禁用</a-tag>
      </a-descriptions-item>
      <a-descriptions-item label="创建人">{{ dataDetail?.createUserString }}</a-descriptions-item>
      <a-descriptions-item label="创建时间">{{ dataDetail?.createTime }}</a-descriptions-item>
      <a-descriptions-item label="修改人">{{ dataDetail?.updateUserString }}</a-descriptions-item>
      <a-descriptions-item label="修改时间">{{ dataDetail?.updateTime }}</a-descriptions-item>
      <a-descriptions-item label="描述" :span="2">{{ dataDetail?.description }}</a-descriptions-item>
    </a-descriptions>
  </a-drawer>
</template>

<script setup lang="ts">
import { useWindowSize } from '@vueuse/core'
import { type TenantResp, getTenant as getDetail } from '@/apis/tenant/management'
import { useMenu } from '@/hooks/app'

const { menuList, getTenantPackageMenuList } = useMenu()
const { width } = useWindowSize()

const dataId = ref('')
const dataDetail = ref<TenantResp>()
const visible = ref(false)

// 查询详情
const getDataDetail = async () => {
  const { data } = await getDetail(dataId.value)
  dataDetail.value = data
}

// 打开
const onOpen = async (id: string) => {
  dataId.value = id
  if (!menuList.value.length) {
    await getTenantPackageMenuList()
  }
  await getDataDetail()
  visible.value = true
}

defineExpose({ onOpen })
</script>

<style scoped lang="scss"></style>
