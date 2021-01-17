<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

  export default {
    name: 'app',
    components: {
    }
  }
</script>

<style>
  html, body {
    background-color: #ffffff;
    margin: 0;
    padding: 0;
    height: 100%;
  }
  #app {
    height: 100%;
  }
</style>
