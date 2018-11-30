<template>
    <div class="wrapper" @click="gotoNextPage">
        <text class="title">this is goods-list page</text>
    </div>
</template>

<script>
    let navigator = weex.requireModule('navigator');
    export default {
        name: 'goods-list',
        data: {

        },
        methods: {
            gotoNextPage: function () {
                navigator.push({
                    url:"file://assets/dist/modules/goods-detail.js",
                    animated:"true"
                })
            }
        }
    }
</script>

<style scoped>

</style>
