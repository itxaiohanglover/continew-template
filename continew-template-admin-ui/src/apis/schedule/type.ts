/** 任务类型 */
export interface JobResp {
  id: number
  groupName: string
  jobName: string
  description?: string
  triggerType: number
  triggerInterval: string
  executorType: number
  taskType: number
  executorInfo: string
  argsStr?: string
  argsType?: string
  routeKey: number
  blockStrategy: number
  executorTimeout: number
  maxRetryTimes: number
  retryInterval: number
  parallelNum: number
  jobStatus: number
  nextTriggerAt?: Date
  createDt?: Date
  updateDt?: Date
}
export interface JobQuery {
  groupName: string
  jobName?: string
  jobStatus?: number
}
export interface JobPageQuery extends JobQuery, PageQuery {}

/** 任务日志类型 */
export interface JobLogResp {
  id: number
  groupName: string
  jobName: string
  jobId: number
  taskBatchStatus: number
  operationReason: number
  executorType: number
  executorInfo: string
  executionAt: string
  createDt: string
}
export interface JobLogQuery {
  jobId?: number
  groupName?: string
  jobName?: string
  taskBatchStatus?: number
  datetimeRange?: Array<string>
}
export interface JobLogPageQuery extends JobLogQuery, PageQuery {}
