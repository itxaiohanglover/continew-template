<template>
  <a-avatar v-if="src" :size="size">
    <img :src="src" :alt="alt" />
    <template v-if="trigger" #trigger-icon><slot name="trigger-icon"></slot></template>
  </a-avatar>
  <a-avatar
    v-else-if="name || text"
    :size="size"
    :style="{
      backgroundColor: avatarColor,
    }"
  >
    <span v-if="name">{{ avatarName }}</span>
    <span v-else>{{ text }}</span>
    <template v-if="trigger" #trigger-icon><slot name="trigger-icon"></slot></template>
  </a-avatar>
  <a-avatar v-else :size="size">
    <img :src="Unknown" :alt="alt" />
    <template v-if="trigger" #trigger-icon><slot name="trigger-icon"></slot></template>
  </a-avatar>
</template>

<script setup lang="ts">
import Unknown from '@/assets/images/avatar/unknown.png'
import * as Regexp from '@/utils/regexp'

defineOptions({ name: 'Avatar' })

const props = withDefaults(defineProps<Props>(), {
  color: '#168CFF',
  size: 20,
  alt: 'avatar',
  trigger: false,
})

interface Props {
  src?: string
  name?: string
  text?: string
  color?: string
  size?: string | number
  alt?: string
  trigger?: boolean
}

/**
 * 英文开头：取传入字符串的前面两个字符，例如：Charles => Ch
 * 超过 4 位：取字符串第一个字符，例如：系统管理员 => 系
 * 其他：取传入字符串的最后两个字符，例如：张三 => 张三；王多鱼：多鱼
 */
const avatarName = computed(() => {
  const name = props.name
  if (!name) return ''
  if (name[0].match(Regexp.OnlyEn)) {
    const nameArr = name.split(' ')
    if (nameArr.length > 1 && nameArr[1][0].match(Regexp.OnlyEn)) return `${nameArr[0][0]}${nameArr[1][0]}`
    return name.substring(0, 2)
  }
  if (name.length > 4) return name[0]
  return name.substring(name.length - 2, name.length)
})

const colors = [
  '#168CFF',
  '#7BC616',
  '#14C9C9',
  '#FF7D00',
  '#FFC72E',
]
const avatarColor = computed(() => {
  const hash = (s) => {
    let hash = 0
    for (let i = 0; i < s.length; i++) {
      hash = (hash << 5) - hash + s.charCodeAt(i)
    }
    return Math.abs(hash)
  }
  return colors[hash(props.name || props.text) % (colors.length)]
})
</script>

<style scoped lang="less"></style>
