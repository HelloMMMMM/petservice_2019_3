package com.hellom.petserviceandroid.article;

import com.hellom.petserviceandroid.base.ResponseBean;

import java.util.List;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class ArticleListBean extends ResponseBean {

    private List<ArticleBean> articleList;

    public List<ArticleBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleBean> articleList) {
        this.articleList = articleList;
    }

    public static class ArticleBean {
        /**
         * articleContent : 现代人养宠物，养的最多的非狗莫属。狗与人的关系比较复杂，作为宠物的经典例子，分析狗与人的关系，也可以认为是在分析宠物与人的关系。值得收藏的一篇文章，如果你正在养宠物请一定进来看看狗的历史意义在春秋时代，人们养过许多动物，养的是“鸡豚狗彘”为代表的六畜。注啊，在以前的平民百姓家里，狗主要是作为六畜中的一种来养的，而六畜是养来干嘛的？养来是作为食物储备的。
         这个年代里，温饱问题都没有解决，快饿死了怎么办？只能杀狗吃肉，一般人是不会把狗当成宠物来养，也养不起宠物。
         值得收藏的一篇文章，如果你正在养宠物请一定进来看看
         随着人类的进步，到了现代，普通人的温饱问题基本得到解决。物质需求满足了，得去解决精神需求。现在，人们养宠物主要是满足精神需求，具体原因各不相同。
         值得收藏的一篇文章，如果你正在养宠物请一定进来看看
         原因一：真无聊。别人都宅在家里，不好意思打扰，而自己总觉得上网聊天不大真实，所以养只狗。你说为什么不养猫？一个人在家多没安全感，养只大型犬吓唬人。
         值得收藏的一篇文章，如果你正在养宠物请一定进来看看
         原因二：养只宠物带小孩。由于自己要上班，孩子挺无聊的。为了不让小孩拆家，养只狗跟小孩做个伴，也可以增加许多乐趣。
         值得收藏的一篇文章，如果你正在养宠物请一定进来看看
         原因三：跟风养狗。这些人看到了养狗的乐趣，却没看到其麻烦，如果主人没有类似与前两个原因的刚需，宠物多半是养不下去的。如果是狗，会变成流浪狗进入人的生活区，引起许多矛盾。
         如果将狗视为食物储备。这时候，狗是没有精神地位的。
         猎人带狗去打猎。狗是猎人的工具，代替了人的部分功能，存在极少的合作共生关系。在这个时候，养狗是为了解决物质需求，狗的地位算得上是猎人的伙伴。
         如果养狗是为了打发时间、增加生活乐趣。这时候，狗满足了人的精神需求，填充了人的精神世界，这时候，狗的地位是宠物，是人的精神延伸。
         如果把狗当孩子养。狗还没有这么聪明，能完全代替人，任何宠物都不具备精神传承功能。再回过头来恶意揣测一番，如果提出这个观点的不是宠物店老板，那么这个人就是把自己的精神延伸当成独立个体，寻求自我满足。
         值得收藏的一篇文章，如果你正在养宠物请一定进来看看
         以上纯属官话。
         你了解怎么和动物相处了吗？愿你能与你的爱宠相处融洽，它能带给你更多的快乐。
         * articleTitle : 测试文章
         * id : 2
         * storeId : 6
         */

        private String articleContent;
        private String articleTitle;
        private int id;
        private int storeId;

        public String getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }
}
