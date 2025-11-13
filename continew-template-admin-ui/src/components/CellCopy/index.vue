<template>
  <a-space :size="[2]">
    <a-typography-paragraph
      :ellipsis="{
        rows: 1,
        showTooltip: true,
        css: true,
      }"
    >
      {{ content }}
    </a-typography-paragraph>
    <a-typography-paragraph copyable :copy-text="content" />
  </a-space>
</template>

<script setup lang="ts">
defineOptions({ name: 'CellCopy' })

withDefaults(defineProps<Props>(), {
  content: '',
})

interface Props {
  content: string
}
</script>

<style scoped lang="scss"></style>
