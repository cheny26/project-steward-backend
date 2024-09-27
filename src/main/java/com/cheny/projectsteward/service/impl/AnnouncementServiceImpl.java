package com.cheny.projectsteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.model.entity.Announcement;
import com.cheny.projectsteward.service.AnnouncementService;
import com.cheny.projectsteward.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【announcement(公告表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

}




