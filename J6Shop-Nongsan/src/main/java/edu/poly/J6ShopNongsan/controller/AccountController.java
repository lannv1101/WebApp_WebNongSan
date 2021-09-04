package edu.poly.J6ShopNongsan.controller;


import edu.poly.J6ShopNongsan.entity.Account;
import edu.poly.J6ShopNongsan.entity.Addresses;
import edu.poly.J6ShopNongsan.model.AccountDTO;
import edu.poly.J6ShopNongsan.model.AddressDTO;
import edu.poly.J6ShopNongsan.service.AccountService;
import edu.poly.J6ShopNongsan.service.AddressesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("lanmarket")
public class AccountController {
    @Autowired
    HttpSession session;
    @Autowired
    AddressesService addressesService;
    @Autowired
    AccountService accountService;

    @GetMapping("account")
    public String account(){
        return "site/account/account";
    }
    @GetMapping("account/profile")
    public String profile(){
        return "site/account/profile";
    }
    @GetMapping("account/addresses")
    public String adresses(Model model){

        String username = (String) session.getAttribute("username");
        List<Addresses> listAddresses= addressesService.findAddressesByAccount_Username(username);

        Optional<Account> account = accountService.findByUsername(username);
        boolean usePrimaryAddressAsDefault = true;
        for (Addresses addresses: listAddresses){
            if (addresses.getAddress_default()==true){
                usePrimaryAddressAsDefault =false;
                break;
            }
        }


        model.addAttribute("account",account.get());
        model.addAttribute("usePrimaryAddressAsDefault",usePrimaryAddressAsDefault);
        model.addAttribute("listaddress",listAddresses);


        return "site/account/addresses";
    }

    @GetMapping("account/addressbook/new")
    public String newAddress(Model model){


        model.addAttribute("address",new AddressDTO());
        return "site/account/address_form";
    }
    @PostMapping("account/addressbook/save")
    public String saveAddress(Model model, @ModelAttribute("address") AddressDTO dto, RedirectAttributes ra){
        String username = (String) session.getAttribute("username");
        List<Addresses> listAddresses= addressesService.findAddressesByAccount_Username(username);
        Account ac = new Account();
        ac.setUsername(username);
        Addresses entity = new Addresses();
        BeanUtils.copyProperties(dto,entity);
        entity.setAccount(ac);

        addressesService.save(entity);
        ra.addFlashAttribute("success","Lưu địa chỉ thành công");

        return "redirect:/lanmarket/account/addresses";
    }
    @GetMapping("account/addressbook/edit/{id}")
    public String editAddress(@PathVariable ("id") Integer addressId,Model model){
        String username = (String) session.getAttribute("username");
        Addresses addresses = addressesService.findByIdAndAccount(addressId,username);
        model.addAttribute("address",addresses);
        return "site/account/address_form";
    }
    @GetMapping("account/addressbook/delete/{id}") ///delete van chua dc
    public String deleteAddress(@PathVariable ("id") Integer addressId,Model model,RedirectAttributes ra){
        String username = (String) session.getAttribute("username");
         addressesService.deleteById(addressId);
        ra.addFlashAttribute("success","Xóa địa chỉ thành công");
        return "redirect:/lanmarket/account/addresses";
    }
    @PostMapping("account/addressbook/setdefault/{id}")
    public String setDefaultAddress(@PathVariable ("id") Integer addressId, Model model,RedirectAttributes ra){


        ra.addFlashAttribute("success","Đặt làm địa chỉ giao hàng thành công");
        return "redirect:/lanmarket/account/addresses";
    }

}
