<template>
  <h1>Hello welcome Component a</h1>
  <el-button @click="handleShowForm">Show Form</el-button>
  <WelcomeB ref="drake" v-if="isShowFormAddLink" />
</template>

<script setup lang="ts">
  import WelcomeB from '@/components/Welcome/WelcomeB.vue'
  import { nextTick, onMounted, ref } from 'vue'

  const drake = ref(WelcomeB)

  const isShowFormAddLink = ref(false)

  const handleShowForm = async () => {
    isShowFormAddLink.value = true
    // await nextTick()
    drake.value.isUpdate = true
    drake.value?.getDetailInfo({
      username: 'Tran Quoc Dat'
      , password: 'Hello@123'
      , retypePassword: 'Hello@123'
      , age: 22, startTime: ''
      , endTime: null
      , imageUrl: 'https://images.pexels.com/photos/1525041/pexels-photo-1525041.jpeg'
    });
    drake.value?.getSayHello();
  }


</script>

<style scoped lang="scss"></style>
