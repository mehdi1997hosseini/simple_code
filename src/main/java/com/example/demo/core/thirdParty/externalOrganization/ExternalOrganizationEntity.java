package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.entity.BasicEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationEntity extends BasicEntity<String> {

    @Enumerated(EnumType.STRING)
    @Column(name = "EXTERNAL_ORGANIZATION_NAME", updatable = false, nullable = false)
    private ExternalOrganizationName orgName;           // نام سازمان خارجی
    private String authUrl;        // URL برای دریافت توکن
    private String clientId;       // شناسه کلاینت (Client ID)
    private String clientSecret;   // رمز کلاینت (Client Secret)
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;      // نوع توکن (مثل Bearer یا JWT)
    private String username;
    private String password;

//    @Enumerated(EnumType.STRING)
//    private GrantType grantType;
//    private List<String> scopes;   // لیستی از دسترسی‌ها یا Scope های مجاز
//    private int tokenExpirySeconds; // زمان انقضا به ثانیه (مقدار ثابت یا دریافت‌شده از سرویس)
//    private Instant tokenExpiryTime; // زمان دقیق انقضا توکن (برای محاسبه زمان دقیق تعویض)
//    private boolean isTokenValid;  // برای بررسی وضعیت اعتبار توکن


}
