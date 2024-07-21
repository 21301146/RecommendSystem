<template>
    <div class="bgView">
        <head-bar :title="newTitle" :set="newSet" :is-back="isBack" :is-set="isSet"></head-bar>
        <div class="bgScored">
            <div>
                <img class="headerImg" src="https://zycdianshang.oss-cn-beijing.aliyuncs.com/1.jpg?Expires=1713269658&OSSAccessKeyId=TMP.3KdyTBB6Hn7retx45rrwJJvQGS52eW7UZTdm7BFQQShn8BKYuuWLynEFcTh7JBMqz1KiXskP38PUdjUs176kXyERogcLmK&Signature=L%2F4QqziKNfMB6VWg0%2Fn4czW3F18%3D">
                <div class="grade-box">
                    <div class="score">商品评分</div>
                    <div class="bgImg">
                        <img v-for="(star, index) in stars" v-bind:key="index" :src="star.src"
                            v-on:click="rating(index, 'starts')" alt="星星图片" />
                    </div>
                </div>
            </div>
            <div class="scoreInfo">{{ scoreInfo }}</div>
        </div>
        <div class="line"></div>
        <div class="inputText">
            <textarea v-bind:maxlength="Surplus" @input="descArea" v-model="inputText" name="abstract" id="abstract"
                placeholder="宝贝满足你的期待吗？说说你的使用心得，分享给想买的他们吧！"></textarea>
        </div>
        <div class="line2"></div>
        <div class="severce">
            <div class="attitudeOfService">服务态度</div>
            <div class="bgImg">
                <img v-for="(star, index) in severceStarts" v-bind:key="index" :src="star.src"
                    v-on:click="rating(index, 'severce')" alt="星星图片" />
            </div>
        </div>
        <div class="publish" @click="publicComment">发布</div>
    </div>
</template>

<script>
// import Headbar from '../components/headerBar'
// import uploadImages from '../components/uploadImages'
import starOffImg from "@/assets/imgs/comment_star/star02@2x.png"
import starOnImg from "@/assets/imgs/comment_star/star@2x.png"
import axios from 'axios';
import { mapGetters } from "vuex";


export default {
    data: function () {
        return {
            scoreInfo: '很满意',
            inputText: '',
            Surplus: 140,
            newTitle: '商品评价',
            scoreStartNum: 5, // 商品评分
            severStartNum: 5, // 服务评分
            newSet: '     ',
            isBack: true,
            isSet: true,
            // userData: JSON.parse(localStorage.getItem('user')),
            stars: [{
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            },
            {
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            }
            ],
            severceStarts: [{
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            },
            {
                src: starOnImg,
                active: true
            }, {
                src: starOnImg,
                active: true
            }],
            imgList: []
        }
    },
    components: {
        // 'head-bar': Headbar,
        // 'up-Load': uploadImages
    },
    computed: {
        // 使用 mapGetters 导入 getUser getter
        ...mapGetters(['getUser']),
        // 获取 userID
        getUserId() {
            return this.$store.getters.getUser;
        },
    },
    created() {
        console.log('User:', this.getUser); // 在控制台输出用户信息
    },
    mounted() {

        // console.log(this.userData);
        console.log(this.$store.getters.getUser.userName);

    },
    methods: {
        // 评分
        rating: function (index, string) {
            if (string === 'starts') {
                var total = this.stars.length // 星星总数
                var idx = index + 1 // 这代表选的第idx颗星-也代表应该显示的星星数量
                // 进入if说明页面为初始状态
                if (this.scoreStartNum === 0) {
                    this.scoreStartNum = idx
                    for (var i = 0; i < idx; i++) {
                        this.stars[i].src = starOnImg
                        this.stars[i].active = true
                    }
                } else {
                    // 如果再次点击当前选中的星级-仅取消掉当前星级，保留之前的。
                    if (idx == this.scoreStartNum) {
                        for (var j = index; j < total; j++) {
                            if (j != 0) {
                                this.stars[j].src = starOffImg
                                this.stars[j].active = false
                            }
                        }
                    }
                    // 如果小于当前最高星级，则直接保留当前星级
                    if (idx < this.scoreStartNum) {
                        for (var k = idx; k < this.scoreStartNum; k++) {
                            if (k != 0) {
                                this.stars[k].src = starOffImg
                                this.stars[k].active = false
                            }
                        }
                    }
                    // 如果大于当前星级，则直接选到该星级
                    if (idx > this.scoreStartNum) {
                        for (var m = 0; m < idx; m++) {
                            this.stars[m].src = starOnImg
                            this.stars[m].active = true
                        }
                    }

                    var count = 0 // 计数器-统计当前有几颗星
                    for (var n = 0; n < total; n++) {
                        if (this.stars[n].active) {
                            count++
                        }
                    }
                    this.scoreStartNum = count
                }
                if (this.scoreStartNum === 1) {
                    this.scoreInfo = '很差'
                } else if (this.scoreStartNum === 2) {
                    this.scoreInfo = '差'
                } else if (this.scoreStartNum === 3) {
                    this.scoreInfo = '一般'
                } else if (this.scoreStartNum === 4) {
                    this.scoreInfo = '满意'
                } else if (this.scoreStartNum === 5) {
                    this.scoreInfo = '很满意'
                }
            } else {
                var total1 = this.severceStarts.length // 星星总数
                var idx1 = index + 1 // 这代表选的第idx颗星-也代表应该显示的星星数量
                // 进入if说明页面为初始状态
                if (this.severStartNum == 0) {
                    this.severStartNum = idx1
                    for (var a = 0; a < idx1; a++) {
                        this.severceStarts[a].src = starOnImg
                        this.severceStarts[a].active = true
                    }
                } else {
                    // 如果再次点击当前选中的星级-仅取消掉当前星级，保留之前的。
                    if (idx1 === this.severStartNum) {
                        for (var b = index; b < total1; b++) {
                            if (b != 0) {
                                this.severceStarts[b].src = starOffImg
                                this.severceStarts[b].active = false
                            }
                        }
                    }
                    // 如果小于当前最高星级，则直接保留当前星级
                    if (idx1 < this.severStartNum) {
                        for (var c = idx1; c < this.severStartNum; c++) {
                            if (c != 0) {
                                this.severceStarts[c].src = starOffImg
                                this.severceStarts[c].active = false
                            }
                        }
                    }
                    // 如果大于当前星级，则直接选到该星级
                    if (idx1 > this.severStartNum) {
                        for (var d = 0; d < idx1; d++) {
                            this.severceStarts[d].src = starOnImg
                            this.severceStarts[d].active = true
                        }
                    }

                    var count1 = 0 // 计数器-统计当前有几颗星
                    for (var e = 0; e < total1; e++) {
                        if (this.severceStarts[e].active) {
                            count1++
                        }
                    }
                    this.severStartNum = count1
                }
            }
        },
        descArea() {
            var textVal = this.introduct.length
            this.Surplus = 200 - textVal
        },

        // 发布评论
        publicComment() {
            // this.scoreStartNum 代表商品评分
            // this.severStartNum 代表服务态度
            // this.inputText  代表心得
            // base64的图片流直接传给服务端，服务端传给阿里云，也可前端直接上传，拿到url链接上传到服务端。
            // 从前一个页面获取产品ID
            const product_id = this.$route.query.productID; // 假设从路由参数中获取
            console.log(product_id)


            // 从用户信息对象中获取用户ID
            const user_id = this.$store.getters.getUser.userName;
            console.log(user_id)


            // 构造要发送的数据对象
            const data = {
                user_id: user_id,
                product_id: product_id,
                score: this.scoreStartNum, // 商品评分
                comment: this.inputText // 评论内容
            };


            // 发送 POST 请求到接口
            axios.post('http://localhost:3000/product/score', data)
                .then(response => {
                    // 请求成功处理
                    console.log('评分和评论提交成功:', response.data);
                })
                .catch(error => {
                    // 请求失败处理
                    console.error('评分和评论提交失败:', error);
                });
        }
    },
    name: 'gotoComment'
}
</script>

<style scoped>
.bgView {
    padding: 15px 15px;
}

.headerImg {
    width: 4.8125rem;
    height: 4.8125rem;
}

.score {
    color: #333333;
    font-size: .875rem;
    margin-left: 10px;
    display: inline-block;
}

.bgScored {
    position: relative;
    margin-top: -15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}

.bgImg img {
    margin-left: 5px;
    width: 1.09375rem;
    height: 1.03125rem;
}

.grade-box {
    position: absolute;
    top: 1.6875rem;
    left: 5.0275rem;
    display: inline-block;
}

.bgImg {
    display: inline-block;
    margin-left: 5px;
}

.line {
    margin-top: -5px;
    margin-left: -23px;
    margin-right: -23px;
    background: #c4c3c3;
    height: 1px;
    opacity: 0.3;
}

.scoreInfo {
    color: #999999;
    font-size: .75rem;
}

.inputText {
    width: 100%;
    margin-top: 10px;
    border-bottom: 1px solid #eef1f5;
}

.inputText textarea {
    width: 100%;
    height: 5.5rem;
    /*outline: none;*/
    /*overflow:auto;*/
    border: none;
    font-size: .75rem;
    /*background-attachment:fixed;*/
    /*background-repeat:no-repeat;*/
    /*border-style:solid;*/
    /*border-color:#FFFFFF*/
}

.selectPic {
    margin-top: 10px;
    width: 100%;
}

.unName {
    display: inline-block;
    color: #333333;
    margin-left: 5px;
    vertical-align: middle;
    font-size: .75rem;
}

.bgName {
    width: 100%;
    height: 50px;
    line-height: 50px;
    display: flex;
    align-items: center;
}

.bgName img {
    width: 17px;
    height: 17px;
}

.line2 {
    margin-top: -5px;
    margin-left: -23px;
    margin-right: -23px;
    background: #c4c3c3;
    height: 8px;
    opacity: 0.3;
}

.attitudeOfService {
    color: #333333;
    font-size: .875rem;
    display: inline-block;
}

.severce {
    display: flex;
    height: 60px;
    align-items: center;
}

.publish {
    width: 50%;
    height: 40px;
    background: #3fc9a5;
    color: white;
    font-size: .999rem;
    text-align: center;
    border-radius: 20px;
    line-height: 40px;
    margin: auto;
}
</style>