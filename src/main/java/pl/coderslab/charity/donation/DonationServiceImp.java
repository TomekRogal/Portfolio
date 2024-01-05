package pl.coderslab.charity.donation;


import org.springframework.stereotype.Service;
import pl.coderslab.charity.category.Category;

import java.util.stream.Collectors;
@Service
public class DonationServiceImp implements DonationService{
    @Override
    public String donationInfo(Donation donation) {
        String comment;
        if(donation.getPickUpComment() == "") {
            comment = "Brak uwag";
        } else {
            comment = donation.getPickUpComment();
        }

        return "<b>Przekazujesz:</b> <br>" +
                "Ilość worków: " + donation.getQuantity() +
                "<br> z kategorii: " + donation.getCategories().stream().map(Category::getName).collect(Collectors.joining(", ")) +
                "<br> fundacji: " + donation.getInstitution().getName() +
                "<br><br> <b>Adres dostawy:</b>"+
                "<br> Ulica: " + donation.getStreet()  +
                "<br> Miasto: " + donation.getCity()  +
                "<br> Kod pocztowy: " + donation.getZipCode()  +
                "<br> Numer telefonu: " + donation.getPhoneNumber() +
                "<br> Data odbioru: " + donation.getPickUpDate() +
                "<br> Czas odbioru: " + donation.getPickUpTime() +
                "<br> Uwagi: " + comment;
    }

}
