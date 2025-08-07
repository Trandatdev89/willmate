<template>
  <el-tabs v-model="activeTab" @tab-click="handleClick">
    <el-tab-pane
        v-for="item in allTab"
        :label="item.name"
        :name="item.name"
        :key="item.name"
    ></el-tab-pane>
  </el-tabs>

  <component :is="currentComponent" />
</template>

<script setup lang="ts">
  import { computed, ref } from 'vue'
  import type { TabsPaneContext } from 'element-plus'

  const props = defineProps<{
    allTab: { name: string; value: any }[]
  }>()

  const activeTab = ref<string>(props.allTab[0]?.name || '')

  const currentComponent = computed(() => {
    const found = props.allTab.find((tab) => tab.name === activeTab.value)
    return found?.value || null
  })

  const handleClick = (tab: TabsPaneContext, event: Event) => {
    activeTab.value = tab.name
  }
</script>
