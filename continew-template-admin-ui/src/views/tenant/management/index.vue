<template>
  <GiPageLayout>
    <GiTable
      row-key="id"
      :data="dataList"
      :columns="columns"
      :loading="loading"
      :scroll="{ x: '100%', y: '100%', minWidth: 1000 }"
      :pagination="pagination"
      :disabled-tools="['size']"
      :disabled-column-keys="['name']"
      @refresh="search"
    >
      <template #toolbar-left>
        <a-input-search v-model="queryForm.description" placeholder="搜索名称/描述" allow-clear @search="search" />
        <a-select
          v-model="queryForm.packageId"
          :options="packageList"
          placeholder="请选择套餐"
          style="width: 200px"
          allow-clear
          @change="search"
        />
        <a-button @click="reset">
          <template #icon><icon-refresh /></template>
          <template #default>重置</template>
        </a-button>
      </template>
      <template #toolbar-right>
        <a-button v-permission="['tenant:management:create']" type="primary" @click="onAdd">
          <template #icon><icon-plus /></template>
          <template #default>新增</template>
        </a-button>
      </template>

      <template #code="{ record }">
        <CellCopy :content="record.code" />
      </template>
      <template #status="{ record }">
        <GiCellStatus :status="record.status" />
      </template>
      <template #expireTime="{ record }">
        <span v-if="!record.expireTime">
          <span>永不过期</span>
        </span>
        <span v-else>{{ record.expireTime }}</span>
      </template>
      <template #domain="{ record }">
        <a v-if="record.domain" style="color: rgb(var(--arcoblue-7))" :href="record.domain">{{ record.domain }}</a>
        <span v-else style="color: red" class="text-red-4">未设置</span>
      </template>
      <template #action="{ record }">
        <a-space>
          <a-link v-permission="['tenant:management:get']" title="详情" @click="onDetail(record)">详情</a-link>
          <a-link v-permission="['tenant:management:update']" title="修改" @click="onUpdate(record)">修改</a-link>
          <a-dropdown>
            <a-button v-if="has.hasPermOr(['tenant:management:updateAdminUserPwd', 'tenant:management:delete'])" type="text" size="mini" title="更多">
              <template #icon>
                <icon-more :size="16" />
              </template>
            </a-button>
            <template #content>
              <a-doption v-permission="['tenant:management:updateAdminUserPwd']" title="修改管理员密码" @click="onUpdateAdminUserPwd(record)">修改管理员密码</a-doption>
              <a-doption
                v-permission="['tenant:management:delete']"
                :disabled="record.disabled"
                :title="record.disabled ? '禁止删除' : '删除'"
                @click="onDelete(record)"
              >
                删除
              </a-doption>
            </template>
          </a-dropdown>
        </a-space>
      </template>
    </GiTable>

    <AddModal ref="AddModalRef" @save-success="search" />
    <DetailDrawer ref="DetailDrawerRef" />
    <AdminUserPwdUpdateModal ref="AdminUserPwdUpdateModalRef" @save-success="search" />
  </GiPageLayout>
</template>

<script setup lang="ts">
import type { TableInstance } from '@arco-design/web-vue'
import AddModal from './AddModal.vue'
import AdminUserPwdUpdateModal from './AdminUserPwdUpdateModal.vue'
import DetailDrawer from './DetailDrawer.vue'
import { type TenantQuery, type TenantResp, deleteTenant, listTenant } from '@/apis/tenant/management'
import { useTable } from '@/hooks'
import { isMobile } from '@/utils'
import has from '@/utils/has'
import { listTenantPackageDict } from '@/apis/tenant'
import type { LabelValueState } from '@/types/global'

defineOptions({ name: 'TenantManagement' })

const queryForm = reactive<TenantQuery>({
  description: undefined,
  packageId: undefined,
  status: undefined,
  sort: ['createTime,desc'],
})

const {
  tableData: dataList,
  loading,
  pagination,
  search,
  handleDelete,
} = useTable((page) => listTenant({ ...queryForm, ...page }), { immediate: true })
const columns: TableInstance['columns'] = [
  {
    title: '序号',
    width: 66,
    align: 'center',
    render: ({ rowIndex }) => h('span', {}, rowIndex + 1 + (pagination.current - 1) * pagination.pageSize),
    fixed: !isMobile() ? 'left' : undefined,
  },
  { title: '编码', dataIndex: 'code', slotName: 'code', width: 150 },
  { title: '名称', dataIndex: 'name', slotName: 'name', ellipsis: true, tooltip: true },
  { title: '套餐', dataIndex: 'packageName', slotName: 'packageName' },
  { title: '域名', dataIndex: 'domain', slotName: 'domain', ellipsis: true, tooltip: true },
  { title: '过期时间', dataIndex: 'expireTime', slotName: 'expireTime', width: 180 },
  { title: '管理员用户', dataIndex: 'adminUsername', slotName: 'adminUsername', ellipsis: true, tooltip: true },
  { title: '状态', dataIndex: 'status', slotName: 'status' },
  { title: '描述', dataIndex: 'description', ellipsis: true, tooltip: true },
  { title: '创建人', dataIndex: 'createUserString', ellipsis: true, tooltip: true, show: false },
  { title: '创建时间', dataIndex: 'createTime', width: 180 },
  { title: '修改人', dataIndex: 'updateUserString', ellipsis: true, tooltip: true, show: false },
  { title: '修改时间', dataIndex: 'updateTime', width: 180, show: false },
  {
    title: '操作',
    dataIndex: 'action',
    slotName: 'action',
    width: 160,
    align: 'center',
    fixed: !isMobile() ? 'right' : undefined,
    show: has.hasPermOr(['tenant:management:get', 'tenant:management:update', 'tenant:management:delete', 'tenant:management:updateAdminUserPwd']),
  },
]

// 重置
const reset = () => {
  queryForm.description = undefined
  queryForm.packageId = undefined
  queryForm.status = undefined
  search()
}

// 删除
const onDelete = (record: TenantResp) => {
  return handleDelete(() => deleteTenant(record.id), {
    content: `是否确定删除租户「${record.name}(${record.code})」？`,
    showModal: true,
  })
}

const AddModalRef = ref<InstanceType<typeof AddModal>>()
// 新增
const onAdd = () => {
  AddModalRef.value?.onAdd()
}

// 修改
const onUpdate = (record: TenantResp) => {
  AddModalRef.value?.onUpdate(record.id)
}

const DetailDrawerRef = ref<InstanceType<typeof DetailDrawer>>()
// 详情
const onDetail = (record: TenantResp) => {
  DetailDrawerRef.value?.onOpen(record.id)
}

const AdminUserPwdUpdateModalRef = ref<InstanceType<typeof AdminUserPwdUpdateModal>>()
// 修改管理员密码
const onUpdateAdminUserPwd = (record: TenantResp) => {
  AdminUserPwdUpdateModalRef.value?.open(record.id)
}

const packageList = ref<LabelValueState[]>([])
// 查询套餐列表
const getPackageList = async () => {
  const { data } = await listTenantPackageDict()
  packageList.value = data
}

onMounted(() => {
  getPackageList()
})
</script>

<style scoped lang="scss"></style>
