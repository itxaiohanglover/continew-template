<template>
  <a-modal
    v-model:visible="visible"
    title="修改租户管理员密码"
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

<script setup lang="ts">
import { Message } from '@arco-design/web-vue'
import { useWindowSize } from '@vueuse/core'
import { updateTenantAdminUserPwd } from '@/apis/tenant/management'
import { type ColumnItem, GiForm } from '@/components/GiForm'
import { useResetReactive } from '@/hooks'
import { encryptByRsa } from '@/utils/encrypt'

const emit = defineEmits<{
  (e: 'save-success'): void
}>()

const { width } = useWindowSize()

const dataId = ref('')
const visible = ref(false)
const formRef = ref<InstanceType<typeof GiForm>>()

const [form, resetForm] = useResetReactive({
  password: undefined,
})

const columns: ColumnItem[] = reactive([
  {
    label: '新密码',
    field: 'password',
    type: 'input-password',
    span: 24,
    required: true,
    props: {
      maxLength: 32,
    },
  },
])

// 重置
const reset = () => {
  formRef.value?.formRef?.resetFields()
  resetForm()
}

// 保存
const save = async () => {
  try {
    const isInvalid = await formRef.value?.formRef?.validate()
    if (isInvalid) return false
    await updateTenantAdminUserPwd({
      password: encryptByRsa(form.password) || '',
    }, dataId.value)
    Message.success('修改成功')
    emit('save-success')
    return true
  } catch (error) {
    return false
  }
}

const open = async (id: string) => {
  reset()
  dataId.value = id
  visible.value = true
}

defineExpose({ open })
</script>

<style scoped lang="scss"></style>
