<template>
  <div class="wrapper">
    <image :src="logo" class="logo" @click="navigateToNextPage"/>
    <text class="greeting">The environment is ready!</text>
    <HelloWorld/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'
import navigateTo from "./tools/router";
export default {
    name: 'App',
    components: {
      HelloWorld
    },
    data () {
      return {
        logo: 'https://gw.alicdn.com/tfs/TB1yopEdgoQMeJjy1XaXXcSsFXa-640-302.png'
      }
    },
    methods: {
      navigateToNextPage: function () {
          navigateTo("goods-list");
      }
    }
}
</script>

<style scoped>
  .wrapper {
    justify-content: center;
    align-items: center;
  }
  .logo {
    width: 424px;
    height: 200px;
  }
  .greeting {
    text-align: center;
    margin-top: 70px;
    font-size: 50px;
    color: #41B883;
  }
  .message {
    margin: 30px;
    font-size: 32px;
    color: #727272;
  }
</style>
