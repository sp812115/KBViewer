package com.koubou.kbviewer.util;

import com.koubou.kbviewer.entity.Episode;
import com.koubou.kbviewer.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class TestSources {

    List<Video> videos =new ArrayList<>();

    List <Episode> episodes=new ArrayList<>();

    public TestSources() {
        Video video1 =new Video("Re：从零开始的异世界生活 新编集版"
                ,"http://css.njhzmxx.com/acg/2019/12/13/20191213052003114.jpg"
                ,"http://www.yhdm.tv/show/4824.html"
                ,"《Re：从零开始的异世界生活 新编集版》2016年播出的TV动画《Re：从零开始的异世界生活》决定新增1小时节目！追加新剪辑，作为1小时节目进行再编辑的“新编辑版”《Re:从零开始的异世界生活》将于2020年1月1日(周三)起在AT-X、TOKYO MX、BS11等播出。");

        Video video2 =new Video("星掠者"
                ,"http://css.njhzmxx.com/acg/2020/01/09/20200109011303689.jpg"
                ,"http://www.yhdm.tv/show/4853.html"
                ,"《星掠者》电视动画《星掠者》改编自水无月崇创作的同名漫画作品，于2018年2月18日宣布了动画化的决定，由GEEKTOYS负责动画制作，2020年1月播出。 被称为“废弃战争”之后的未曾有过的大战之后的世界艾尔西亚。在那里，强者从弱者手里夺取证实自己存在的“数字计数”，是一个弱肉强食的世界。 在那样的世界里，2人相遇了。一个是用面具隐藏身份、非常喜欢H的事情、为了弱者而挥动大刀的青年·离人。另一个是依靠母亲的遗言踏上寻找“传说中的击坠王”旅途的少女·阳菜。 以2人的相遇为契机，以“数字计数”支配一切的世界之谜变得逐渐明朗。");

        Video video3 =new Video("转生恶役只好拔除破灭旗标"
                ,"http://css.njhzmxx.com/acg/2019/11/16/20191116085828591.jpg"
                ,"http://www.yhdm.tv/show/4788.html"
                ,"《转生恶役只好拔除破灭旗标》电视动画《转生成为了只有乙女游戏破灭 Flag 的邪恶大小姐》改编自山口悟著作，ひだかなみ负责插画的同名轻小说，于2018年10月19日宣布了动画化企划进行中的消息。 动画由SILVER LINK 负责动画制作，于2020年4月开始播出。 公爵千金卡塔莉娜·克拉艾斯在脑袋撞到石头上时取回了前世的记忆，发现这里是前世所迷恋的乙女游戏《FORTUNE LOVER》的世界，自己成为了妨碍游戏主人公恋情的邪恶大小姐！ 在游戏里，克拉艾斯被准备的好的结局是流放国外，最差的是被杀死。无论如何都要回避这个破灭Flag，一定要取得幸福的未来给你看！！ 没错吧？万人迷的爱情喜剧拉开了帷幕。");

        videos.add(video1);
        videos.add(video2);
        videos.add(video3);

        Episode episode1=new Episode("第一集"
                ,"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_f7abfea4371335d251d9f8d3d2803cf0.mp4");

        Episode episode2=new Episode("第二集"
                ,"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_84def2dbca9847631106560fceac4185.mp4");

        Episode episode3=new Episode("第三集"
                ,"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_0208de424d906ac3f69f1e227c70823c.mp4");

        Episode episode4=new Episode("第四集"
                ,"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_94427543424dd1547172df4b052ae3da.mp4");

        Episode episode5=new Episode("第五集"
                ,"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/607272_2a42d0cb4807aefb1204d6b8df708cdd.mp4");

        episodes.add(episode1);
        episodes.add(episode2);
        episodes.add(episode3);
        episodes.add(episode4);
        episodes.add(episode5);

    }

    public List<Video> getVideos(){
        return videos;
    }

    public List<Episode> getEpisodes(){return episodes;}
}
