<template>
  <a-space fill>
    <Avatar :src="props.avatar" :name="props.name" :size="24" />
    <a-link v-if="props.isLink" @click="emit('click')">
      <a-typography-paragraph
        class="link-text"
        :ellipsis="{
          rows: 1,
          showTooltip: true,
          css: true,
        }"
      >
        {{ props.name }}
      </a-typography-paragraph>
    </a-link>
    <span v-else>
      <a-typography-paragraph
        :ellipsis="{
          rows: 1,
          showTooltip: true,
          css: true,
        }"
      >
        {{ props.name }}
      </a-typography-paragraph>
    </span>
  </a-space>
</template>

<script setup lang="ts">
defineOptions({ name: 'GiCellAvatar' })

const props = withDefaults(defineProps<Props>(), {
  avatar: '',
  name: '',
  isLink: false, // 是否可以点击
})

const emit = defineEmits<{
  (e: 'click'): void
}>()

interface Props {
  avatar?: string
  name: string
  isLink?: boolean
}
</script>

<style scoped lang="scss"></style>
