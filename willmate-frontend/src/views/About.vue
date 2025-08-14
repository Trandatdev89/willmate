<template>
  <div class="container">
    <DataTable
      ref="tableUser"
      :get-data-function="getPageUser"
      :row-class-name="getRowClass"
      @sortChange="handleSortChange"
    >
      <el-table-column type="selection" width="55" prop="id" />

      <el-table-column prop="name" label="name" sortable />

      <el-table-column prop="age" label="age" sortable />

      <el-table-column prop="address" label="address" />

      <el-table-column prop="phone" label="phone" />

      <el-table-column prop="email" label="email" />

      <el-table-column>
        <template #header>
          <span>Action</span>
        </template>
        <template #default="scope">
          <el-button type="primary">Action</el-button>
        </template>
      </el-table-column>
    </DataTable>
  </div>
</template>

<script lang="ts" setup>
import DataTable from '@/modules/DataTable.vue'
import { dataUser } from '@/constant/InitData.ts'
import type { User } from '@/common/typeData.ts'
import { type Ref, ref, watchEffect } from 'vue'

const tableUser = ref<InstanceType<typeof DataTable> | null>(null)
const dataTableUser = ref<User[]>(dataUser)

const getPageUser = (pageRequest: any) => {
  pageRequest.keywordSearch = 'test-one'
  return dataTableUser
}

const getRowClass = ({ row }: any) => {
  // row.isDelete ? "delete-row" : "";
}

const handleSortChange = ({ propSort, orderSort }: { propSort: Ref; orderSort: Ref }) => {
  console.log(propSort.value)
  console.log(orderSort.value)
}

// watchEffect(() => {
//   tableUser.value.testOne();
// })
</script>

<style lang="scss" scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
}
</style>
