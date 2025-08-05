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
  >
     <slot></slot>
  </el-table>
</template>

<script lang="ts" setup>
  import { onMounted, ref } from 'vue'
  import type { User } from '@/common/typeData.ts'

  const tableRef = ref(null);

  const loading = ref<boolean>(false);

  const data = ref<User[]>([]);

  const emit = defineEmits(["sort-change"]);

  const currentPage = ref<number>(1);
  const totalPage = ref<number>(1);
  const totalElement = ref<number>(0);
  const size = ref<number>(3);
  const selectedRows = ref([]);
  const propSort = ref<string>("");
  const orderSort = ref("descending");

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

  const onSortChange=(sort:{prop:string,order:string})=>{
    propSort.value = sort.prop;
    orderSort.value = sort.order;
    emit('sort-change',{propSort,orderSort});
  }

  const  handleSelectChange=(val: any[])=>{
      console.log(val)
  }

  const getData = ()=>{
    const request={
      page: currentPage.value,
      size: size.value
    };
    loading.value = true;
    const  res = props.getDataFunction(request);
    data.value = res.value;
    loading.value = false;
  }

  const testOne =()=>{
    console.log("hello world")
  }

  const reload =()=>{

  }

  onMounted(()=>{
    getData();
  })

  defineExpose({getData,selectedRows,reload,testOne})

</script>

<style lang="scss" scoped>

</style>