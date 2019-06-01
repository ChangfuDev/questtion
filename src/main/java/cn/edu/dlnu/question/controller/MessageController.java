package cn.edu.dlnu.question.controller;

import cn.edu.dlnu.question.entity.Message;
import cn.edu.dlnu.question.result.LayUiResultDataList;
import cn.edu.dlnu.question.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/message")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/list")
  public LayUiResultDataList list(@RequestParam("page") Integer page,
      @RequestParam("limit") Integer limit) {
    return messageService.list(page,limit);
  }

  @PostMapping("/add")
  public boolean add(@RequestParam("title") String title,
      @RequestParam("content") String content) {
    return messageService.add(new Message(title, content));
  }

  @GetMapping("/id/{id}")
  public Message getMessage(@PathVariable("id") Integer id) {
    return messageService.getMessage(id);
  }

  @PostMapping("update")
  public boolean update(@RequestParam("id") Integer id, @RequestParam("title") String title,
      @RequestParam("content") String content) {
    Message message = new Message(id,title,content);
    return messageService.update(message);
  }
  @PostMapping("/delete/{ids}")
  public boolean delete(@PathVariable("ids")Integer[] ids){
    return messageService.delete(ids);
  }

  @GetMapping("/wx")
  public LayUiResultDataList wx(){
    return  messageService.wx();
  }
}
