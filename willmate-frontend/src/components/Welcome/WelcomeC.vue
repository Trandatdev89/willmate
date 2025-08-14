<template>
  <Ckeditor ref="refCkEditor" class="edit-text"></Ckeditor>
</template>

<script setup lang="ts">
   import Ckeditor from "../../modules/Ckeditor.vue";
   import { ref } from 'vue'

   const refCkEditor = ref();
</script>

<style scoped lang="scss">

</style>
