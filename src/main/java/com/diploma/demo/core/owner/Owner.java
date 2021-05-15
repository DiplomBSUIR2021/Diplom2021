package com.diploma.demo.core.owner;

import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.ownership.Ownership;
import com.diploma.demo.core.rightofuse.RightOfUse;
import com.diploma.demo.core.stateregistration.StateRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
@Audited

public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="type")
    private String type;

    @Column(name = "name")
    private String name;

    //дата рождения(госрегистрации юрлица)
    @Column(name="registration_date")
    private LocalDate registrationDate;

    @Column(name="birth_date")
    private LocalDate birthDate;

    //тип документа, удостоверяющего личность(юрлица)
    @Column(name ="doc_type")
    private String docType;

    //Серия (при наличии), номер документа
    @Column(name="doc_n")
    private String docN;

    @OneToOne
    @JoinColumn(name="owner_id")
    private StateRegistration docRegistration;

    //личный номер (УНП для юрлица) ///  registration_number or personal_number??
    @Column(name = "registration_number")
    private String registrationNumber;

    /*@Column(name = "personal_number")
    private String personalNumber;*/

    @Embedded
    private Address registrationAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "region", column = @Column(name = "post_region")),
            @AttributeOverride(name = "city", column = @Column(name = "post_city")),
            @AttributeOverride(name = "street", column = @Column(name = "post_street")),
            @AttributeOverride(name = "homeNumber", column = @Column(name = "post_home_number")),
            @AttributeOverride(name = "apartmentn", column = @Column(name = "post_apartment_n")),
    })
    @Embedded
    private Address postAddress;

    //Номер ПТН
    @Column(name = "ptn_n")
    private String ptnN;

    @Column(name="phone_number")
    @Pattern(regexp = "\\+375[0-9]{9}")
    private String phoneNumber;

    @Column(name="telegram_number")
    @Pattern(regexp = "\\+375[0-9]{9}")
    private String telegramNumber;

    @Column(name="viber_number")
    @Pattern(regexp = "\\+375[0-9]{9}")
    private String viberNumber;

    @Column(name="whatsapp_number")
    @Pattern(regexp = "\\+375[0-9]{9}")
    private String whatsappNumber;

    @OneToOne
    @JoinColumn(name="ownership_id")
    private Ownership ownership;

    @OneToOne
    @JoinColumn(name = "right_of_use_id")
    private RightOfUse rightOfUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_plot_id")
    private LandPlot landPlot;

}
