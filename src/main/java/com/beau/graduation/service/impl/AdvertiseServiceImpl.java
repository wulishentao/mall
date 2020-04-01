package com.beau.graduation.service.impl;

import com.beau.graduation.common.PageList;
import com.beau.graduation.dao.AdvertiseDao;
import com.beau.graduation.model.Advertise;
import com.beau.graduation.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类
 * AdvertiseServiceImpl
 * @author beau
 * @date 2020/03/28
 */
@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    AdvertiseDao dao;

    @Override
    public int insert(Advertise advertise) {
        return dao.insert(advertise);
    }

    @Override
    public int batchInsert(List<Advertise> list) {
    	return dao.batchInsert(list);
    }

    @Override
    public int update(Advertise advertise) {
    	return dao.update(advertise);
    }

    @Override
    public int delete(Advertise advertise) {
    	return dao.delete(advertise);
    }

    @Override
    public int batchDelete(List<Advertise> list) {
        return dao.batchDelete(list);
    }

	@Override
	public Advertise selectByObj(Advertise advertise) {
		return dao.selectByObj(advertise);
	}

	@Override
	public List<Advertise> selectList(Advertise advertise) {
		return dao.selectList(advertise);
	}

	@Override
	public PageList<Advertise> selectPage(Advertise advertise, Integer offset, Integer pageSize) {
		PageList<Advertise> pageList = new PageList<>();

		int total = this.total(advertise);

		Integer totalPage;
		if (total % pageSize != 0) {
			totalPage = (total /pageSize) + 1;
		} else {
			totalPage = total /pageSize;
		}

		int page = (offset - 1) * pageSize;

		List<Advertise> list = dao.selectPage(advertise, page, pageSize);

		pageList.setList(list);
		pageList.setStartPageNo(offset);
		pageList.setPageSize(pageSize);
		pageList.setTotalCount(total);
		pageList.setTotalPageCount(totalPage);
		return pageList;
	}

	@Override
	public int total(Advertise advertise) {
		return dao.total(advertise);
	}
}