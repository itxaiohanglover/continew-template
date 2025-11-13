import autoImport from 'unplugin-auto-import/vite'

export default function createAutoImport() {
  return autoImport({
    // 自动导入 vue 相关函数
    imports: ['vue', 'vue-router', {
      // vue 3.5.x
      vue: ['useTemplateRef', 'onWatcherCleanup', 'useId'],
    }],
    dts: './src/types/auto-imports.d.ts',
  })
}
