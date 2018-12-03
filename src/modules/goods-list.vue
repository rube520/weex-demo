<template>
    <div class="wrapper" @click="gotoNextPage">
        <text class="title">this is goods-list page</text>
    </div>
</template>

<script>
    import navigateTo from "../tools/router";
    export default {
        name: 'goods-list',
        data: {

        },
        methods: {
            gotoNextPage: function () {
                navigateTo("goods-detail");
            }
        }
    }
</script>

<style scoped>

</style>
