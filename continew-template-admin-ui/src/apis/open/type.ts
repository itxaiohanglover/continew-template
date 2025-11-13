/** 应用类型 */
export interface AppResp {
  id: string
  name: string
  accessKey: string
  secretKey: string
  expireTime: string
  description: string
  status: 1 | 2
  createUserString: string
  createTime: string
  updateUserString: string
  updateTime: string
}

export interface AppQuery {
  description?: string
  sort: Array<string>
}

export interface AppPageQuery extends AppQuery, PageQuery {}
