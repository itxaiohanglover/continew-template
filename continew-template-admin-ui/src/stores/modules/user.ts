import { defineStore } from 'pinia'
import { computed, reactive, ref } from 'vue'
import { resetRouter } from '@/router'
import {
  type AccountLoginReq,
  AuthTypeConstants,
  // 邮箱登录已注释：根据后端移除邮箱登录功能
  // type EmailLoginReq,
  // 手机号登录已注释：根据后端轻量化需求
  // type PhoneLoginReq,
  type UserInfo,
  accountLogin as accountLoginApi,
  // 邮箱登录已注释：根据后端移除邮箱登录功能
  // emailLogin as emailLoginApi,
  getUserInfo as getUserInfoApi,
  logout as logoutApi,
  // 手机号登录已注释：根据后端轻量化需求
  // phoneLogin as phoneLoginApi,
  // 三方登录已注释：根据后端移除三方登录功能
  // socialLogin as socialLoginApi,
} from '@/apis'
import { clearToken, getToken, setToken } from '@/utils/auth'
import { resetHasRouteFlag } from '@/router/guard'

const storeSetup = () => {
  // 多租户下线：移除租户 Store
  const userInfo = reactive<UserInfo>({
    id: '',
    username: '',
    nickname: '',
    gender: 0,
    email: '',
    phone: '',
    avatar: '',
    pwdResetTime: '',
    pwdExpired: false,
    registrationDate: '',
    deptName: '',
    roles: [],
    permissions: [],
  })
  const nickname = computed(() => userInfo.nickname)
  const username = computed(() => userInfo.username)
  const avatar = computed(() => userInfo.avatar)

  const token = ref(getToken() || '')
  const pwdExpiredShow = ref<boolean>(true)
  const roles = ref<string[]>([]) // 当前用户角色
  const permissions = ref<string[]>([]) // 当前角色权限标识集合
  // 重置token
  const resetToken = () => {
    token.value = ''
    clearToken()
    resetHasRouteFlag()
  }

  // 登录
  const accountLogin = async (req: AccountLoginReq, tenantCode?: string) => {
    const res = await accountLoginApi({ ...req, clientId: import.meta.env.VITE_CLIENT_ID, authType: AuthTypeConstants.ACCOUNT }, tenantCode)
    setToken(res.data.token)
    // 多租户下线：不再设置租户ID
    token.value = res.data.token
  }

  // 邮箱登录（已注释：根据后端移除邮箱登录功能）
  // const emailLogin = async (req: EmailLoginReq, tenantCode?: string) => {
  //   const res = await emailLoginApi({ ...req, clientId: import.meta.env.VITE_CLIENT_ID, authType: AuthTypeConstants.EMAIL }, tenantCode)
  //   setToken(res.data.token)
  //   // 多租户下线：不再设置租户ID
  //   token.value = res.data.token
  // }

  // 手机号登录（已注释：根据后端轻量化需求）
  // const phoneLogin = async (req: PhoneLoginReq, tenantCode?: string) => {
  //   const res = await phoneLoginApi({ ...req, clientId: import.meta.env.VITE_CLIENT_ID, authType: AuthTypeConstants.PHONE }, tenantCode)
  //   setToken(res.data.token)
  //   // 多租户下线：不再设置租户ID
  //   token.value = res.data.token
  // }

  // 三方账号登录（已注释：根据后端移除三方登录功能）
  // const socialLogin = async (source: string, req: any) => {
  //   const res: any = await socialLoginApi({ ...req, source, clientId: import.meta.env.VITE_CLIENT_ID, authType: AuthTypeConstants.SOCIAL })
  //   setToken(res.data.token)
  //   // 多租户下线：不再设置租户ID
  //   token.value = res.data.token
  // }

  // 退出登录回调
  const logoutCallBack = async () => {
    roles.value = []
    permissions.value = []
    pwdExpiredShow.value = true
    resetToken()
    resetRouter()
    // 多租户下线：无需重置租户ID
  }

  // 退出登录
  const logout = async () => {
    try {
      await logoutApi()
      await logoutCallBack()
      return true
    } catch (error) {
      return false
    }
  }

  // 获取用户信息
  const getInfo = async () => {
    const res = await getUserInfoApi()
    Object.assign(userInfo, res.data)
    userInfo.avatar = res.data.avatar
    if (res.data.roles && res.data.roles.length) {
      roles.value = res.data.roles
      permissions.value = res.data.permissions
    }
  }

  return {
    userInfo,
    nickname,
    username,
    avatar,
    token,
    roles,
    permissions,
    pwdExpiredShow,
    accountLogin,
    // 邮箱登录已注释：根据后端移除邮箱登录功能
    // emailLogin,
    // 手机号登录已注释：根据后端轻量化需求
    // phoneLogin,
    // 三方登录已注释：根据后端移除三方登录功能
    // socialLogin,
    logout,
    logoutCallBack,
    getInfo,
    resetToken,
  }
}

export const useUserStore = defineStore('user', storeSetup, {
  persist: { paths: ['token', 'roles', 'permissions', 'pwdExpiredShow'], storage: localStorage },
})
