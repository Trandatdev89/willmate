<template>
  <Common :allTab="allTab"></Common>
</template>

<script lang="ts" setup>
  import Common from '@/views/Common.vue'
  import { markRaw, ref } from 'vue'
  import WelcomeA from '@/components/Welcome/WelcomeA.vue'
  import WelcomeB from '@/components/Welcome/WelcomeB.vue'
  import WelcomeC from '@/components/Welcome/WelcomeC.vue'
  import WelcomeD from '@/components/Welcome/WelcomeD.vue'

  const initComponent = [
    {
      name: 'User',
      value: markRaw(WelcomeA)
    },
    {
      name: 'Config',
      value: markRaw(WelcomeB)
    },
    {
      name: 'Setting',
      value: markRaw(WelcomeC)
    },
    {
      name: 'Task',
      value: markRaw(WelcomeD)
    },
  ]

  const allTab = ref(initComponent);

</script>

<style lang="scss" scoped>

</style>