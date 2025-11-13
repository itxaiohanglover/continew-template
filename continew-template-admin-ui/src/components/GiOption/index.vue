<template>
  <ul class="gi-option">
    <slot></slot>
  </ul>
</template>

<script setup lang="ts">
defineOptions({ name: 'GiOption' })
</script>

<style scoped lang="scss">
.gi-option {
  width: 100%;
  min-width: 120px;
}
</style>
