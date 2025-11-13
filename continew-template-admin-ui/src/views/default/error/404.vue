<template>
  <ErrorPage :code="404" />
</template>

<script setup lang="ts">
import ErrorPage from './components/ErrorPage.vue'

defineOptions({ name: 'Error404' })
</script>

<style scoped lang="scss"></style>
