<template>
  <a-modal
    v-model:visible="visible"
    :title="title"
    :mask-closable="false"
    :esc-to-close="false"
    :width="width >= 500 ? 500 : '100%'"
    draggable
    @before-ok="save"
    @close="reset"
  >
    <GiForm ref="formRef" v-model="form" :columns="columns" />
  </a-modal>
</template>

<script setup lang="tsx">
import { Message } from '@arco-design/web-vue'
import { useWindowSize } from '@vueuse/core'
import { addTenant, getTenant, updateTenant } from '@/apis/tenant/management'
import { type ColumnItem, GiForm } from '@/components/GiForm'
import { useResetReactive } from '@/hooks'
import { encryptByRsa } from '@/utils/encrypt'
import { listTenantPackageDict } from '@/apis/tenant'
import type { LabelValueState } from '@/types/global'

const emit = defineEmits<{
  (e: 'save-success'): void
}>()

const { width } = useWindowSize()

const dataId = ref('')
const visible = ref(false)
const isUpdate = computed(() => !!dataId.value)
const title = computed(() => (isUpdate.value ? '修改租户' : '新增租户'))
const formRef = ref<InstanceType<typeof GiForm>>()

const packageList = ref<LabelValueState[]>([])
// 查询租户套餐
const getPackageList = async () => {
  const { data } = await listTenantPackageDict()
  packageList.value = data
}

const [form, resetForm] = useResetReactive({
  status: 1,
})

const columns: ColumnItem[] = reactive([
  {
    label: '名称',
    field: 'name',
    type: 'input',
    span: 24,
    required: true,
    props: {
      maxLength: 30,
    },
  },
  {
    label: () => (
      <a-tooltip content="例如：example.com">
        域名&nbsp;
        <icon-question-circle />
      </a-tooltip>
    ),
    field: 'domain',
    type: 'input',
    span: 24,
    slots: {
      prepend: () => (
        <span style={{ textAlign: 'center' }}>http(s)://</span>
      ),
    },
    props: {
      placeholder: '请输入域名',
    },
  },
  {
    label: '过期时间',
    field: 'expireTime',
    type: 'date-picker',
    span: 24,
    props: {
      showTime: true,
    },
  },
  {
    label: () => (
      <a-tooltip content="自动初始化管理员用户">
        管理员用户&nbsp;
        <icon-question-circle />
      </a-tooltip>
    ),
    field: 'adminUsername',
    type: 'input',
    span: 24,
    required: true,
    props: {
      placeholder: '请输入管理员用户',
      maxLength: 64,
    },
    hide: () => {
      return isUpdate.value
    },
  },
  {
    label: '管理员密码',
    field: 'adminPassword',
    type: 'input-password',
    span: 24,
    required: true,
    props: {
      maxLength: 32,
    },
    hide: () => {
      return isUpdate.value
    },
  },
  {
    label: '套餐',
    field: 'packageId',
    type: 'select',
    span: 24,
    required: true,
    hide: () => {
      return isUpdate.value
    },
    props: {
      options: packageList,
    },
  },
  {
    label: '描述',
    field: 'description',
    type: 'textarea',
    span: 24,
  },
  {
    label: '状态',
    field: 'status',
    type: 'switch',
    span: 24,
    props: {
      type: 'round',
      checkedValue: 1,
      uncheckedValue: 2,
      checkedText: '启用',
      uncheckedText: '禁用',
    },
  },
])

// 重置
const reset = () => {
  formRef.value?.formRef?.resetFields()
  resetForm()
  getPackageList()
}

// 保存
const save = async () => {
  try {
    const isInvalid = await formRef.value?.formRef?.validate()
    if (isInvalid) return false
    if (isUpdate.value) {
      await updateTenant(form, dataId.value)
      Message.success('修改成功')
    } else {
      await addTenant({
        ...form,
        adminPassword: encryptByRsa(form.adminPassword),
      })
      Message.success('新增成功')
    }
    emit('save-success')
    return true
  } catch (error) {
    return false
  }
}

// 新增
const onAdd = async () => {
  reset()
  dataId.value = ''
  visible.value = true
}

// 修改
const onUpdate = async (id: string) => {
  reset()
  dataId.value = id
  const { data } = await getTenant(id)
  Object.assign(form, data)
  visible.value = true
}

defineExpose({ onAdd, onUpdate })
</script>

<style scoped lang="scss"></style>
