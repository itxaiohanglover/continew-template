<template>
  <div id="videoId"></div>
</template>

<script setup lang="ts">
import Player from 'xgplayer'
import type { FileItem } from '@/apis/system'

interface Props {
  data: FileItem
}
const props = withDefaults(defineProps<Props>(), {})

onMounted(() => {
  new Player({
    id: 'videoId',
    url: props.data?.url ?? '',
    lang: 'zh-cn',
    autoplay: true,
    closeVideoClick: true,
    videoInit: true,
  })
})
</script>

<style scoped lang="scss"></style>
