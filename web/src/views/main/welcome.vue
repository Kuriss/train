<template>
  <div style="border: 4px solid orange;
              border-radius: 4px;
              padding: 10px;">
    <h3><b>铁路购票系统功能说明</b></h3>
    本项目实现了高性能的铁路购票系统，解决了购票过程中的高并发问题，支持如下技术：
    <br>
    <li>利用<b>CDN</b>提高用户访问页面速度</li>
    <li>利用<b>分布式缓存</b>提供高性能的余票查询，解决缓存穿透、击穿、雪崩问题</li>
    <li>采用<b>前端验证码</b>减少瞬时高峰请求压力</li>
    <li>使用<b>后端验证码</b>防止机器人抢票</li>
    <li>使用<b>限流技术</b>快速响应无效请求，提升系统性能</li>
    <li>通过<b>令牌发放技术</b>控制抢票流量</li>
    <li>采用<b>分布式锁技术</b>防止超卖，确保票务数据准确性</li>
    <li>使用<b>异步削峰+排队机制</b>解决吞吐量问题，优化用户体验</li>
    <li>使用<b>分布式事务</b>确保数据一致性，防止库存数据不一致</li>
  </div>

  <a-divider style="border-color: #7cb305" dashed />

  <div style="border: 4px solid #99CCFF;
              border-radius: 4px;
              padding: 10px;">
    <h3><b>会员端功能说明</b></h3>
    <div>
      如果您还没有乘车人信息，可以先去
      <router-link to="/passenger">添加乘车人</router-link>
    </div>
    <div>
      购票功能支持选座，您可以去
      <router-link to="/ticket">查询余票并购票</router-link>。
      购票成功后，可以查看
      <router-link to="/my-ticket">我的车票</router-link>；
      查看
      <router-link to="/seat">座位销售图</router-link>了解各个座位的销售情况。
    </div>
    <div style="font-size: 14px; color: red">
      目前系统未开放管理员功能，以防止恶意修改车次数据影响体验。
    </div>
    <div style="font-size: 14px; color: #888888">
      小提示：为了便于体验，本系统未对重复购票做校验，同一个人可以重复购票。
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: "welcome-view",
  setup() {
    return {};
  },
});
</script>
