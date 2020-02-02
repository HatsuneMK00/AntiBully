package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.HelpInfo;
import xyz.makise.antibully.backend.mapper.HelpInfoMapper;

import java.util.List;

@Service
public class HelpInfoService {
    final
    HelpInfoMapper helpInfoMapper;

    public HelpInfoService(HelpInfoMapper helpInfoMapper) {
        this.helpInfoMapper = helpInfoMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<HelpInfo> getAllHelpInfos() {
        try {
            return helpInfoMapper.getAllHelpInfos();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public int makeHelpInfoHandled(int infoId) {
        try {
            return helpInfoMapper.makeHelpInfoHandled(infoId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }
}
