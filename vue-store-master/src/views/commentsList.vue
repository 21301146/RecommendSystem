<!-- <template>
    <div class="goods-comment">
        <div class="head">
            <div class="data">
                <p><span>100</span><span>人购买</span></p>
                <p><span>99.99%</span><span>好评率</span></p>
            </div>
            <div class="tags">
                <div class="dt">大家都在说：</div>
                <div class="dd">
                    <a href="javascript:;" class="active">全部评价（1000）</a>
                    <a href="javascript:;">好吃（1000）</a>
                    <a href="javascript:;">便宜（1000）</a>
                    <a href="javascript:;">很好（1000）</a>
                    <a href="javascript:;">再来一次（1000）</a>
                    <a href="javascript:;">快递棒（1000）</a>
                </div>
            </div>
        </div>
        <div class="sort">
            <span>排序：</span>
            <a href="javascript:;" class="active">默认</a>
            <a href="javascript:;">最新</a>
            <a href="javascript:;">最热</a>
        </div>
        <div class="list"></div>
    </div>
</template>
<script>
export default {
    name: 'GoodsComment'
}
</script>
<style scoped>
.goods-comment {
    /* .head styles */
    display: flex;
    padding: 30px 0;

    .head .data {
        width: 340px;
        display: flex;
        padding: 20px;

        .head .data p {
            flex: 1;
            text-align: center;
        }

        .head .data p span:first-child {
            font-size: 32px;
            /* Use the actual color value instead of @priceColor */
            color: #ff0000;
            /* For example */
        }

        .head .data p span:last-child {
            color: #999;
        }
    }

    /* .tags styles */
    .head .tags {
        flex: 1;
        display: flex;
        border-left: 1px solid #f5f5f5;
    }

    .head .tags .dt {
        font-weight: bold;
        width: 100px;
        text-align: right;
        line-height: 42px;
    }

    .head .tags .dd {
        flex: 1;
        display: flex;
        flex-wrap: wrap;
    }

    .head .tags .dd>a {
        width: 132px;
        height: 42px;
        margin-left: 20px;
        margin-bottom: 20px;
        border-radius: 4px;
        border: 1px solid #e4e4e4;
        background: #f5f5f5;
        color: #999;
        text-align: center;
        line-height: 40px;
    }

    .head .tags .dd>a:hover {
        border-color: #ff0000;
        /* For example */
        background: lighten(#ff0000, 50%);
        /* For example */
        color: #ff0000;
        /* For example */
    }

    .head .tags .dd>a.active {
        border-color: #ff0000;
        /* For example */
        background: #ff0000;
        /* For example */
        color: #fff;
    }

    /* .sort styles */
    .sort {
        height: 60px;
        line-height: 60px;
        border-top: 1px solid #f5f5f5;
        border-bottom: 1px solid #f5f5f5;
        margin: 0 20px;
        color: #666;
    }

    .sort>span {
        margin-left: 20px;
    }

    .sort>a {
        margin-left: 30px;
    }

    .sort>a.active,
    .sort>a:hover {
        color: #ff0000;
        /* For example */
    }
}
</style> -->


<template>
    <div>
        <p class="commentsList">商品评价列表</p>
        <ul class="review-list">
            <li v-for="(review, index) in reviews" :key="index" class="review-item">
                <div class="user-id">{{ review.userId }}</div>
                <div class="review-content">
                    <p class="score">评分: {{ review.score }}</p>
                    <p class="comment"> {{ review.comment }}</p>
                    <p class="rating-time">{{ formatTime(review.ratingTime) }}</p>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            reviews: [
                {
                    userId: 'admin12345',
                    score: 4,
                    comment: "不错不错，下次还会来买的。",
                    ratingTime: 1622419200000 // 时间戳，表示评价时间
                },
                {
                    userId: '114514',
                    score: 5,
                    comment: "质量很好，物美价廉。",
                    ratingTime: 1622505600000
                },
                {
                    userId: 'dddd',
                    score: 3,
                    comment: "一般般，不算太满意。",
                    ratingTime: 1622592000000
                },
                {
                    userId: 'user5678',
                    score: 4,
                    comment: "还可以，挺好的。",
                    ratingTime: 1622419200000
                },
                {
                    userId: 'test998',
                    score: 5,
                    comment: "非常满意，值得购买。",
                    ratingTime: 1622505600000
                },
                {
                    userId: 'guest234',
                    score: 3,
                    comment: "一般般，性价比一般。",
                    ratingTime: 1622592000000
                },
                {
                    userId: 'buyer777',
                    score: 4,
                    comment: "购买了很多次了，还是不错的。",
                    ratingTime: 1622678400000
                },
                {
                    userId: 'shopper456',
                    score: 5,
                    comment: "非常满意，物流速度也很快。",
                    ratingTime: 1622764800000
                }
            ]
        };
    },
    mounted() {
        // 发送HTTP请求获取评价数据
        axios.get('/product/comment')
            .then(response => {
                this.reviews = response.data;
            })
            .catch(error => {
                console.error('Error fetching reviews:', error);
            });
    },
    methods: {
        formatTime(timestamp) {
            // 格式化时间戳
            const date = new Date(timestamp);
            return date.toLocaleString();
        }
    }
};
</script>

<style scoped>
.commentsList {
    margin-left: 40px;
    margin-top: 20px;
    font-size: 3ch;
    font-weight: bold;

}

.review-list {
    margin-left: 80px;
    margin-top: 50px;
    list-style: none;
    padding: 0;
}

.review-item {
    margin-bottom: 40px;
    padding-bottom: 10px;
    border-bottom: 1px solid #ccc;
    display: flex;
}

.user-id {
    flex: 0 0 100px;
    /* 固定宽度 */
    font-weight: bold;
    display: grid;
    place-items: center;
    text-align: center;
}

.review-content {
    flex-grow: 1;
    /* 剩余空间平均分配 */
    margin-left: 50px;
}

.score,
.comment {
    margin: 5px 0;
    margin-bottom: 15px;
}

.comment {
    font-size: 2ch;
}

.rating-time {
    margin-top: 5px;
    margin-bottom: 5px;
    opacity: 0.8;
    font-size: 1ch;
    /* 降低透明度 */
}
</style>