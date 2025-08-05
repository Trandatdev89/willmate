<template>
  <div class="container">
    <DataTable ref="tableUser"
               :get-data-function="getPageAnimal"
               :row-class-name="getRowClass">
      <el-table-column type="selection" width="55" />

      <el-table-column #default="scope" label="name">
        {{ scope.row.name }}
      </el-table-column>

      <el-table-column #default="scope" label="Flood">
        {{ scope.row.numberEdd }}
      </el-table-column>

    </DataTable>
  </div>
</template>

<script lang="ts" setup>
  import DataTable from '@/modules/DataTable.vue'
  import type { Animal } from '@/common/typeData.ts'
  import { ref } from 'vue'
  import { dataAnimal } from '@/constant/InitData.ts'

  const dataTableAnimal =ref<Animal[]>(dataAnimal);

  const getPageAnimal=(pageRequest:any)=>{
    pageRequest.linkIcon = "hello world";
    return dataTableAnimal;
  }

  const getRowClass=({row}:any)=>{
     // console.log(row);
  }

</script>

<style lang="scss" scoped>
  .container {
    max-width: 800px;
    margin: 0 auto;
  }
</style>