<!-- 三方登录回调已注释：根据后端移除三方登录功能 -->
<template>
  <a-spin :loading="loading" :tip="'功能已下线'">
    <div></div>
  </a-spin>
</template>

<script setup lang="ts">
// 三方登录回调已注释：根据后端移除三方登录功能
// import { Message } from '@arco-design/web-vue'
// import { useRoute, useRouter } from 'vue-router'
// import { bindSocialAccount } from '@/apis/system'
// import { useTabsStore, useUserStore } from '@/stores'
// import { isLogin } from '@/utils/auth'

// const route = useRoute()
// const router = useRouter()
// const userStore = useUserStore()
// const tabsStore = useTabsStore()
// const source = route.query.source as string
const loading = ref(true)

// 三方账号登录已注释：根据后端移除三方登录功能
// const handleSocialLogin = () => {
//   if (loading.value) return
//   loading.value = true
//   const { redirect, ...othersQuery } = router.currentRoute.value.query
//   userStore
//     .socialLogin(source, othersQuery)
//     .then(() => {
//       tabsStore.reset()
//       router.push({
//         path: (redirect as string) || '/',
//         query: {
//           ...othersQuery,
//         },
//       })
//       Message.success('欢迎使用')
//     })
//     .catch(() => {
//       router.push({
//         name: 'Login',
//         query: {
//           ...othersQuery,
//         },
//       })
//     })
//     .finally(() => {
//       loading.value = false
//     })
// }

// 绑定三方账号已注释：根据后端移除三方登录功能
// const handleBindSocial = () => {
//   if (loading.value) return
//   loading.value = true
//   const { ...othersQuery } = router.currentRoute.value.query
//   bindSocialAccount(source, othersQuery)
//     .then(() => {
//       router.push({
//         path: '/user/profile',
//         query: {
//           ...othersQuery,
//         },
//       })
//       Message.success('绑定成功')
//     })
//     .catch(() => {
//       router.push({
//         path: '/user/profile',
//         query: {
//           ...othersQuery,
//         },
//       })
//     })
//     .finally(() => {
//       loading.value = false
//     })
// }

// if (isLogin()) {
//   handleBindSocial()
// } else {
//   handleSocialLogin()
// }

// 直接跳转到登录页
onMounted(() => {
  setTimeout(() => {
    window.location.href = '/login'
  }, 2000)
})
</script>

<style scoped lang="less">
:deep(.arco-spin-mask) {
  background-color: transparent;
}

div {
  width: 150px;
  height: 150px;
  position: absolute;
  left: 50%;
  top: 45%;
  margin-left: -50px;
  margin-top: -50px;
}
</style>
