<template>
  <GiSvgIcon :name="props.icon" :size="18" />
</template>

<script setup lang="ts">
interface Props {
  icon?: string
}

const props = withDefaults(defineProps<Props>(), {})
</script>

<style scoped lang="scss"></style>
