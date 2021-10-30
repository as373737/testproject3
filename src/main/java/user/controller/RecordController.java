package com.web.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class RecordController {
	
	IRecordService recordservice;

	@Autowired
	public RecordController(IRecordService recordservice) {
		//888888999
		this.recordservice = recordservice;
	}
	
	
	
	
	
	@GetMapping({"/user"})
	public String record(Model model) {
		 return "record/user";
	}
	
	@GetMapping("/select")
    public String getAddNewMemberForm1(@ModelAttribute("RecordBean") RecordBean record ,Model model) {		
//		record.setBuyer("asd123");
		model.addAttribute("record",record);
        System.out.println("buyer= "+record.getBuyer());
		return "record/select";
    }

	
	@PostMapping("/select")
	public String list(@ModelAttribute("record") RecordBean record ,Model model) {
		String buyer= record.getBuyer();
		System.out.println("buyer2 =" +buyer);		
		List<RecordBean> list = recordservice.getAllRecords(buyer);
		model.addAttribute("select", list);
		return "record/select2";	
	}
	
	
	
	
	@GetMapping("/delete")
	public String delete(@ModelAttribute("RecordBean") RecordBean record,Model model) {
		model.addAttribute("delete",record);
		return "record/delete";
		
	}
	
	@PostMapping("/delete")
	public String delete2(@ModelAttribute("delete") RecordBean record ,Model model) {
		Integer rid= record.getRecord_id();
		System.out.println("rid2 =" +rid);	
		recordservice.deleteById(rid);
		return "record/delete2";
		
	}
	
	
	
	
	@GetMapping("/update")
	public String update(@ModelAttribute("RecordBean") RecordBean record ,Model model ) {
		RecordBean recordBean =new RecordBean();
		model.addAttribute("update",record);
		recordBean.setRecord_id(20);;
		recordBean.setPid(2);
		recordBean.setPcount(100);
		System.out.println("rid = "+ recordBean.getRecord_id()+",pid = "+recordBean.getPid()+",cnt = "+recordBean.getPcount());
		return "record/update";
		
	}
	
	@PostMapping("/update")
	public String update2(@ModelAttribute("update") RecordBean record ,Model model) {
		Integer record_id = record.getRecord_id();
		Integer pid = record.getPid();
		Integer pcount = record.getPcount();
		System.out.println("rid = "+ record_id+",pid = "+pid+",cnt = "+pcount);
		recordservice.update(record);
		return "record/update2";
		
	}

	
	

}
