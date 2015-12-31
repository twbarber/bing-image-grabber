package jig.bing.enums;

/**
 * Market enumeration for Bing Search Parameters.
 */
public enum Market {

  AR_XA("ar-XA"),
  BG_BG("bg-BG"),
  CS_CZ("cs-CZ"),
  DA_DK("da-DK"),
  DE_AT("de-AT"),
  DE_CH("de-CH"),
  DE_DE("de-DE"),
  EL_GR("el-GR"),
  EN_AU("en-AU"),
  EN_CA("en-CA"),
  EN_GB("en-GB"),
  EN_ID("en-ID"),
  EN_IE("en-IE"),
  EN_IN("en-IN"),
  EN_MY("en-MY"),
  EN_NZ("en-NZ"),
  EN_PH("en-PH"),
  EN_SG("en-SG"),
  EN_US("en-US"),
  EN_XA("en-XA"),
  EN_ZA("en-ZA"),
  ES_AR("es-AR"),
  ES_CL("es-CL"),
  ES_ES("es-ES"),
  ES_MX("es-MX"),
  ES_US("es-US"),
  ES_XL("es-XL"),
  ET_EE("et-EE"),
  FI_FI("fi-FI"),
  FR_BE("fr-BE"),
  FR_CA("fr-CA"),
  FR_CH("fr-CH"),
  FR_FR("fr-FR"),
  HE_IL("he-IL"),
  HR_HR("hr-HR"),
  HU_HU("hu-HU"),
  IT_IT("it-IT"),
  JA_JP("ja-JP"),
  KO_KR("ko-KR"),
  LT_LT("lt-LT"),
  LV_LV("lv-LV"),
  NB_NO("nb-NO"),
  NL_BE("nl-BE"),
  NL_NL("nl-NL"),
  PL_PL("pl-PL"),
  PT_BR("pt-BR"),
  PT_PT("pt-PT"),
  RO_RO("ro-RO"),
  RU_RU("ru-RU"),
  SK_SK("sk-SK"),
  SL_SL("sl-SL"),
  SV_SE("sv-SE"),
  TH_TH("th-TH"),
  TR_TR("tr-TR"),
  UK_UA("uk-UA"),
  ZH_CN("zh-CN"),
  ZH_HK("zh-HK"),
  ZH_TW("zh-TW");

  private final String market;

  Market(final String market) {
    this.market = market;
  }

  @Override
  public String toString() {
    return this.market;
  }
}
