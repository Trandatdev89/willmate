<template>
  <el-table v-if="data"
  v-loading.fullscreen.lock="loading"
  :data="data"
  ref="tableRef"
  :row-class-name="rowClassName"
  empty-text="Data Empty"
  style="width: 100%"
  @sort-change="onSortChange"
  @selection-change="handleSelectChange"
  :header-cell-style="headerCellStyle"
  :header-row-style="headerRowStyle"
  @sortChange="$emit('sort-change',$event)">

  </el-table>
</template>

<script lang="ts" setup>
  import { ref } from 'vue'

  const tableRef = ref(null);

  const loading = ref<boolean>(false);

  const data = ref([]);

  const props = defineProps({
    getDataFunction:{
      type:Function,
      require:true
    },
    rowClassName:{
      type:Function,
      require:true
    },
    headerCellStyle:{
      type:Function,
      require:true
    },
    headerRowStyle:{
      type:Function,
      require:true
    }
  });

  const onSortChange=()=>{

  }

  const  handleSelectChange=()=>{

  }

  const getData = async ()=>{
    const request={
      page:
    }
  }

</script>

<style lang="scss" scoped>

</style>