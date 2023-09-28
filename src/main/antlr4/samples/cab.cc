
/**
 * Интерфейс 3D модели
 */
namespace model {

  /**
   * Кабина 1
   */
  namespace cab1 {
    
    /**
     * Переключатели.
     *   Кабина 1
     */
    enum class sw : unsigned short {
      
      /**
       * Тормоз поезда
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: R3951
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS
       */
      R3951_0 = 0,

      /**
       * Тормоз локомотива
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: R2541
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS
       */
      R2541_1 = 1,

      /**
       * Реверс
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: REV1
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS, KEY
       */
      REV1_2 = 2,

      /**
       * Контроллер
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: KONT1
       * Flags: CONTROLLERX, CHECKDISTANCEONPRESS
       */
      KONT1_3 = 3,

      /**
       * Задатчик тормозного усилия ЭДТ
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: ZAD1
       * Flags: CONTROLLERX, CHECKDISTANCEONPRESS
       */
      ZAD1_4 = 4,

      /**
       * Блокировка тормозов усл. 367
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: R3671
       * Flags: CHECKDISTANCEONPRESS, KEY
       */
      R3671_6 = 6,

      /**
       * Редуктор зарядного давления
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: RED1
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERX
       */
      RED1_7 = 7,

      /**
       * Ключ ЭПК
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: EPK1
       * Flags: CHECKDISTANCEONPRESS
       */
      EPK1_8 = 8,

      /**
       * Прожектор яркий
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV11
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV11_9 = 9,

      /**
       * Прожектор тускло
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV21
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV21_10 = 10,

      /**
       * Питание ЭПТ
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV31
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV31_11 = 11,

      /**
       * ЭПТ
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV41
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV41_12 = 12,

      /**
       * Электрический тормоз
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV51
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV51_13 = 13,

      /**
       * Топливный насос ведомого тепловоза
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV61
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV61_14 = 14,

      /**
       * Топливный насос ведущего тепловоза
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV71
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV71_15 = 15,

      /**
       * Управление тепловозом
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV81
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV81_16 = 16,

      /**
       * Управление общее
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV91
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV91_17 = 17,

      /**
       * Освещение пульта
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV101
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV101_18 = 18,

      /**
       * Освещение кабины
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV111
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV111_19 = 19,

      /**
       * Вентиляция
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: AZV121
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV121_20 = 20,

      /**
       * Жалюзи масло
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB11
       * Flags: CHECKDISTANCEONPRESS
       */
      TB11_21 = 21,

      /**
       * Жалюзи вода
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB21
       * Flags: CHECKDISTANCEONPRESS
       */
      TB21_22 = 22,

      /**
       * ОП II ступень
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB31
       * Flags: CHECKDISTANCEONPRESS
       */
      TB31_23 = 23,

      /**
       * ОП I ступень
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB41
       * Flags: CHECKDISTANCEONPRESS
       */
      TB41_24 = 24,

      /**
       * Освещение приборов
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB51
       * Flags: CHECKDISTANCEONPRESS
       */
      TB51_25 = 25,

      /**
       * Освещение кабины
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB61
       * Flags: CHECKDISTANCEONPRESS
       */
      TB61_26 = 26,

      /**
       * Освещение расписания
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB71
       * Flags: CHECKDISTANCEONPRESS
       */
      TB71_27 = 27,

      /**
       * Электрикалорифер
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB81
       * Flags: CHECKDISTANCEONPRESS
       */
      TB81_28 = 28,

      /**
       * Радиостанция
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB101
       * Flags: CHECKDISTANCEONPRESS
       */
      TB101_29 = 29,

      /**
       * Манометры и термометры
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB111
       * Flags: CHECKDISTANCEONPRESS
       */
      TB111_30 = 30,

      /**
       * АЛСН
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB121
       * Flags: CHECKDISTANCEONPRESS
       */
      TB121_31 = 31,

      /**
       * Частота АСЛН
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB131
       * Flags: CHECKDISTANCEONPRESS
       */
      TB131_32 = 32,

      /**
       * Омыв окон
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB141
       * Flags: CHECKDISTANCEONPRESS
       */
      TB141_33 = 33,

      /**
       * Стеклоочиститель
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TB151
       * Flags: CHECKDISTANCEONPRESS
       */
      TB151_34 = 34,

      /**
       * Вызов помощника
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN41
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN41_35 = 35,

      /**
       * Отпуск тормозов локомотива
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN51
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN51_36 = 36,

      /**
       * Свисток
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN11
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN11_37 = 37,

      /**
       * Тифон
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN21
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN21_38 = 38,

      /**
       * Песок
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN31
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN31_39 = 39,

      /**
       * Рукоятка бдительности
       *   Кабина 1
       * Type: Кнопка
       * Name: RB1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RB1_40 = 40,

      /**
       * Пуск дизеля ведущего тепловоза
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN81
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN81_41 = 41,

      /**
       * Пуск дизеля ведомого тепловоза
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN71
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN71_42 = 42,

      /**
       * Проверка ЭТ
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN91
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN91_43 = 43,

      /**
       * ВК
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN101
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN101_44 = 44,

      /**
       * Проверка АЛСН
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN111
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN111_45 = 45,

      /**
       * Левый БФ
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: TBP71
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERY
       */
      TBP71_46 = 46,

      /**
       * Правый БФ
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: TBP61
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERY
       */
      TBP61_47 = 47,

      /**
       * Номерные знаки
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP51
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP51_48 = 48,

      /**
       * Освещение пульта
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP41
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP41_49 = 49,

      /**
       * Освещение резервное
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP31
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP31_50 = 50,

      /**
       * Калориферы
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP21
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP21_51 = 51,

      /**
       * Вентиляторы
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP11
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP11_52 = 52,

      /**
       * Вольтметр
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: TBP81
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP81_53 = 53,

      /**
       * Тифон
       *   Кабина 1
       * Type: Кнопка
       * Name: BTNP21
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTNP21_54 = 54,

      /**
       * Свисток
       *   Кабина 1
       * Type: Кнопка
       * Name: BTNP11
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTNP11_55 = 55,

      /**
       * Комбинированный кран усл. 103
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: KK1
       * Flags: CONTROLLERX
       */
      KK1_56 = 56,

      /**
       * Маневры
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN121
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN121_57 = 57,

      /**
       * Свисток
       *   Кабина 1
       * Type: Кнопка
       * Name: BTN131
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN131_58 = 58,

      /**
       * РБС
       *   Кабина 1
       * Type: Кнопка
       * Name: RBS1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RBS1_59 = 59,

      /**
       * РБП
       *   Кабина 1
       * Type: Кнопка
       * Name: RBP1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RBP1_60 = 60,

      /**
       * Вызов ДСП
       *   Кабина 1
       * Type: Кнопка
       * Name: DSP1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      DSP1_61 = 61,

      /**
       * Вызов ДНЦ
       *   Кабина 1
       * Type: Кнопка
       * Name: DNC1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      DNC1_62 = 62,

      /**
       * Подсветка
       *   Кабина 1
       * Type: Кнопка
       * Name: PODSV1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      PODSV1_63 = 63,

      /**
       * Питание радиостанции
       *   Кабина 1
       * Type: Кнопка
       * Name: PIT1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      PIT1_64 = 64,

      /**
       * Ручной тормоз
       *   Кабина 1
       * Type: Мультипереключатель
       * Name: RT1
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERX, PRECISE
       */
      RT1_65 = 65,

      /**
       * Завод часов
       *   Кабина 1
       * Type: Выключатель/тумблер
       * Name: SLZAVOD1
       * Flags: CHECKDISTANCEONPRESS
       */
      SLZAVOD1_66 = 66,

    };
  
    /**
     * Состояния приборов
     * Кабина 1
     */
    namespace state {
    
    };
  
    /**
     * Указатели.
     * Кабина 1
     */
    enum class disp : unsigned short {
      
      /**
       * Вольтметр
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR141
       */
      ARR141_0 = 0,

      /**
       * Амперметр АКБ
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR151
       */
      ARR151_1 = 1,

      /**
       * Напряжение генератора
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR161
       */
      ARR161_2 = 2,

      /**
       * Индикатор
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR171
       */
      ARR171_3 = 3,

      /**
       * Ток якоря Тяга
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR51
       */
      ARR51_4 = 4,

      /**
       * Ток якоря ЭДТ
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR61
       */
      ARR61_5 = 5,

      /**
       * Заданная скорость
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR71
       */
      ARR71_6 = 6,

      /**
       * Давление ТЦ
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR81
       */
      ARR81_7 = 7,

      /**
       * Давление ТМ
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR101
       */
      ARR101_8 = 8,

      /**
       * Давление ГР
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR91
       */
      ARR91_9 = 9,

      /**
       * Давление УР
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR111
       */
      ARR111_10 = 10,

      /**
       * Скорость
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR01
       */
      ARR01_11 = 11,

      /**
       * Часы
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR121
       */
      ARR121_12 = 12,

      /**
       * Минуты
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR131
       */
      ARR131_13 = 13,

      /**
       * Температура воды
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR011
       */
      ARR011_14 = 14,

      /**
       * Температура масла
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR21
       */
      ARR21_15 = 15,

      /**
       * Давление топлива
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR31
       */
      ARR31_16 = 16,

      /**
       * Давление масла
       *
       * Кабина 1
       * Type: ARROW
       * Name: ARR41
       */
      ARR41_17 = 17,

      /**
       * Сброс нагрузки
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP011
       */
      LAMP011_20 = 20,

      /**
       * Жалюзи ЭТ открыты
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP21
       */
      LAMP21_21 = 21,

      /**
       * Температура воды и масла
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP31
       */
      LAMP31_22 = 22,

      /**
       * Жалюзи ЭТ открыты
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP41
       */
      LAMP41_23 = 23,

      /**
       * Давление масла
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP51
       */
      LAMP51_24 = 24,

      /**
       * Нет зарядки батареи
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP61
       */
      LAMP61_25 = 25,

      /**
       * ЭДТ I тепловоза
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP71
       */
      LAMP71_26 = 26,

      /**
       * ЭДТ II тепловоза
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP81
       */
      LAMP81_27 = 27,

      /**
       * О
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP111
       */
      LAMP111_28 = 28,

      /**
       * П
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP101
       */
      LAMP101_29 = 29,

      /**
       * Т
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMP91
       */
      LAMP91_30 = 30,

      /**
       * Резервный топливный насос
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPP11
       */
      LAMPP11_31 = 31,

      /**
       * Нагружение на тормозные резисторы
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPP21
       */
      LAMPP21_32 = 32,

      /**
       * Пожар левой стороны ДП
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPP31
       */
      LAMPP31_33 = 33,

      /**
       * Пожар правой стороны ДП
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPP41
       */
      LAMPP41_34 = 34,

      /**
       * Перегрев электрического калорифера
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPP51
       */
      LAMPP51_35 = 35,

      /**
       * ПСС
       *
       * Кабина 1
       * Type: LAMP
       * Name: PSS11
       */
      PSS11_36 = 36,

      /**
       * Скатывание (H)
       *
       * Кабина 1
       * Type: LAMP
       * Name: PSS21
       */
      PSS21_37 = 37,

      /**
       * ПСС
       *
       * Кабина 1
       * Type: LAMP
       * Name: PSS31
       */
      PSS31_38 = 38,

      /**
       * 
       *
       * Кабина 1
       * Type: LAMP
       * Name: LAMPPO1
       */
      LAMPPO1_39 = 39,

    };
  
  };

  /**
   * Кабина 2
   */
  namespace cab2 {
    
    /**
     * Переключатели.
     *   Кабина 2
     */
    enum class sw : unsigned short {
      
      /**
       * Тормоз поезда
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: R395
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS
       */
      R395_100 = 100,

      /**
       * Тормоз локомотива
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: R254
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS
       */
      R254_101 = 101,

      /**
       * Реверс
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: REV
       * Flags: CONTROLLERY, CHECKDISTANCEONPRESS, KEY
       */
      REV_102 = 102,

      /**
       * Контроллер
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: KONT
       * Flags: CONTROLLERX, CHECKDISTANCEONPRESS
       */
      KONT_103 = 103,

      /**
       * Задатчик тормозного усилия ЭДТ
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: ZAD
       * Flags: CONTROLLERX, CHECKDISTANCEONPRESS
       */
      ZAD_104 = 104,

      /**
       * Блокировка тормозов усл. 367
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: R367
       * Flags: CHECKDISTANCEONPRESS, KEY
       */
      R367_106 = 106,

      /**
       * Редуктор зарядного давления
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: RED
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERX
       */
      RED_107 = 107,

      /**
       * Ключ ЭПК
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: EPK
       * Flags: CHECKDISTANCEONPRESS
       */
      EPK_108 = 108,

      /**
       * Прожектор яркий
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV1
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV1_109 = 109,

      /**
       * Прожектор тускло
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV2
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV2_110 = 110,

      /**
       * Питание ЭПТ
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV3
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV3_111 = 111,

      /**
       * ЭПТ
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV4
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV4_112 = 112,

      /**
       * Электрический тормоз
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV5
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV5_113 = 113,

      /**
       * Топливный насос ведомого тепловоза
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV6
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV6_114 = 114,

      /**
       * Топливный насос ведущего тепловоза
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV7
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV7_115 = 115,

      /**
       * Управление тепловозом
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV8
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV8_116 = 116,

      /**
       * Управление общее
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV9
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV9_117 = 117,

      /**
       * Освещение пульта
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV10
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV10_118 = 118,

      /**
       * Освещение кабины
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV011
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV011_119 = 119,

      /**
       * Вентиляция
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: AZV12
       * Flags: CHECKDISTANCEONPRESS
       */
      AZV12_120 = 120,

      /**
       * Жалюзи масло
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB1
       * Flags: CHECKDISTANCEONPRESS
       */
      TB1_121 = 121,

      /**
       * Жалюзи вода
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB2
       * Flags: CHECKDISTANCEONPRESS
       */
      TB2_122 = 122,

      /**
       * ОП II ступень
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB3
       * Flags: CHECKDISTANCEONPRESS
       */
      TB3_123 = 123,

      /**
       * ОП I ступень
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB4
       * Flags: CHECKDISTANCEONPRESS
       */
      TB4_124 = 124,

      /**
       * Освещение приборов
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB5
       * Flags: CHECKDISTANCEONPRESS
       */
      TB5_125 = 125,

      /**
       * Освещение кабины
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB6
       * Flags: CHECKDISTANCEONPRESS
       */
      TB6_126 = 126,

      /**
       * Освещение расписания
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB7
       * Flags: CHECKDISTANCEONPRESS
       */
      TB7_127 = 127,

      /**
       * Электрикалорифер
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB8
       * Flags: CHECKDISTANCEONPRESS
       */
      TB8_128 = 128,

      /**
       * Радиостанция
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB10
       * Flags: CHECKDISTANCEONPRESS
       */
      TB10_129 = 129,

      /**
       * Манометры и термометры
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB011
       * Flags: CHECKDISTANCEONPRESS
       */
      TB011_130 = 130,

      /**
       * АЛСН
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB12
       * Flags: CHECKDISTANCEONPRESS
       */
      TB12_131 = 131,

      /**
       * Частота АСЛН
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB13
       * Flags: CHECKDISTANCEONPRESS
       */
      TB13_132 = 132,

      /**
       * Омыв окон
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB14
       * Flags: CHECKDISTANCEONPRESS
       */
      TB14_133 = 133,

      /**
       * Стеклоочиститель
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TB15
       * Flags: CHECKDISTANCEONPRESS
       */
      TB15_134 = 134,

      /**
       * Вызов помощника
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN4
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN4_135 = 135,

      /**
       * Отпуск тормозов локомотива
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN5
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN5_136 = 136,

      /**
       * Свисток
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN1_137 = 137,

      /**
       * Тифон
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN2
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN2_138 = 138,

      /**
       * Песок
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN3
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN3_139 = 139,

      /**
       * Рукоятка бдительности
       *   Кабина 2
       * Type: Кнопка
       * Name: RB
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RB_140 = 140,

      /**
       * Пуск дизеля ведущего тепловоза
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN8
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN8_141 = 141,

      /**
       * Пуск дизеля ведомого тепловоза
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN7
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN7_142 = 142,

      /**
       * Проверка ЭТ
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN9
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN9_143 = 143,

      /**
       * ВК
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN10
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN10_144 = 144,

      /**
       * Проверка АЛСН
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN011
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN011_145 = 145,

      /**
       * Левый БФ
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: TBP7
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERY
       */
      TBP7_146 = 146,

      /**
       * Правый БФ
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: TBP6
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERY
       */
      TBP6_147 = 147,

      /**
       * Номерные знаки
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP5
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP5_148 = 148,

      /**
       * Освещение пульта
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP4
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP4_149 = 149,

      /**
       * Освещение резервное
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP3
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP3_150 = 150,

      /**
       * Калориферы
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP2
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP2_151 = 151,

      /**
       * Вентиляторы
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP1
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP1_152 = 152,

      /**
       * Вольтметр
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: TBP8
       * Flags: CHECKDISTANCEONPRESS
       */
      TBP8_153 = 153,

      /**
       * Тифон
       *   Кабина 2
       * Type: Кнопка
       * Name: BTNP2
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTNP2_154 = 154,

      /**
       * Свисток
       *   Кабина 2
       * Type: Кнопка
       * Name: BTNP1
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTNP1_155 = 155,

      /**
       * Комбинированный кран усл. 103
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: KK
       * Flags: CONTROLLERX
       */
      KK_156 = 156,

      /**
       * Маневры
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN12
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN12_157 = 157,

      /**
       * Свисток
       *   Кабина 2
       * Type: Кнопка
       * Name: BTN13
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      BTN13_158 = 158,

      /**
       * РБС
       *   Кабина 2
       * Type: Кнопка
       * Name: RBS
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RBS_159 = 159,

      /**
       * РБП
       *   Кабина 2
       * Type: Кнопка
       * Name: RBP
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      RBP_160 = 160,

      /**
       * Вызов ДСП
       *   Кабина 2
       * Type: Кнопка
       * Name: DSP
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      DSP_161 = 161,

      /**
       * Вызов ДНЦ
       *   Кабина 2
       * Type: Кнопка
       * Name: DNC
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      DNC_162 = 162,

      /**
       * Подсветка
       *   Кабина 2
       * Type: Кнопка
       * Name: PODSV
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      PODSV_163 = 163,

      /**
       * Питание радиостанции
       *   Кабина 2
       * Type: Кнопка
       * Name: PIT
       * Flags: CHECKDISTANCEONPRESS, NONFIXED
       */
      PIT_164 = 164,

      /**
       * Ручной тормоз
       *   Кабина 2
       * Type: Мультипереключатель
       * Name: RT
       * Flags: CHECKDISTANCEONPRESS, CONTROLLERX, PRECISE
       */
      RT_165 = 165,

      /**
       * Завод часов
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: SLZAVOD
       * Flags: CHECKDISTANCEONPRESS
       */
      SLZAVOD_166 = 166,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_1
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_1_168 = 168,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_2
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_2_169 = 169,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_3
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_3_170 = 170,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_11
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_11_171 = 171,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_22
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_22_172 = 172,

      /**
       * UNNAMED
       *   Кабина 2
       * Type: Выключатель/тумблер
       * Name: STOR_33
       * Flags: PRECISE, CONTROLLERY, CHECKDISTANCEONPRESS
       */
      STOR_33_173 = 173,

    };
  
    /**
     * Указатели.
     * Кабина 2
     */
    enum class disp : unsigned short {
      
      /**
       * Вольтметр
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR14
       */
      ARR14_100 = 100,

      /**
       * Амперметр АКБ
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR15
       */
      ARR15_101 = 101,

      /**
       * Напряжение генератора
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR16
       */
      ARR16_102 = 102,

      /**
       * Индикатор
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR17
       */
      ARR17_103 = 103,

      /**
       * Ток якоря Тяга
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR5
       */
      ARR5_104 = 104,

      /**
       * Ток якоря ЭДТ
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR6
       */
      ARR6_105 = 105,

      /**
       * Заданная скорость
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR7
       */
      ARR7_106 = 106,

      /**
       * Давление ТЦ
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR8
       */
      ARR8_107 = 107,

      /**
       * Давление ТМ
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR9
       */
      ARR9_108 = 108,

      /**
       * Давление ГР
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR10
       */
      ARR10_109 = 109,

      /**
       * Давление УР
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR11
       */
      ARR11_110 = 110,

      /**
       * Скорость
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR0
       */
      ARR0_111 = 111,

      /**
       * Часы
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR12
       */
      ARR12_112 = 112,

      /**
       * Минуты
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR13
       */
      ARR13_113 = 113,

      /**
       * Температура воды
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR1
       */
      ARR1_114 = 114,

      /**
       * Температура масла
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR2
       */
      ARR2_115 = 115,

      /**
       * Давление топлива
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR3
       */
      ARR3_116 = 116,

      /**
       * Давление масла
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARR4
       */
      ARR4_117 = 117,

      /**
       * Сброс нагрузки
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP1
       */
      LAMP1_120 = 120,

      /**
       * Жалюзи ЭТ открыты
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP2
       */
      LAMP2_121 = 121,

      /**
       * Температура воды и масла
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP3
       */
      LAMP3_122 = 122,

      /**
       * Жалюзи ЭТ открыты
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP4
       */
      LAMP4_123 = 123,

      /**
       * Давление масла
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP5
       */
      LAMP5_124 = 124,

      /**
       * Нет зарядки батареи
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP6
       */
      LAMP6_125 = 125,

      /**
       * ЭДТ I тепловоза
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP7
       */
      LAMP7_126 = 126,

      /**
       * ЭДТ II тепловоза
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP8
       */
      LAMP8_127 = 127,

      /**
       * О
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP11
       */
      LAMP11_128 = 128,

      /**
       * П
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP10
       */
      LAMP10_129 = 129,

      /**
       * Т
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMP9
       */
      LAMP9_130 = 130,

      /**
       * Резервный топливный насос
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPP1
       */
      LAMPP1_131 = 131,

      /**
       * Нагружение на тормозные резисторы
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPP2
       */
      LAMPP2_132 = 132,

      /**
       * Пожар левой стороны ДП
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPP3
       */
      LAMPP3_133 = 133,

      /**
       * Пожар правой стороны ДП
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPP4
       */
      LAMPP4_134 = 134,

      /**
       * Перегрев электрического калорифера
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPP5
       */
      LAMPP5_135 = 135,

      /**
       * ПСС
       *
       * Кабина 2
       * Type: LAMP
       * Name: PSS1
       */
      PSS1_136 = 136,

      /**
       * Скатывание (H)
       *
       * Кабина 2
       * Type: LAMP
       * Name: PSS2
       */
      PSS2_137 = 137,

      /**
       * ПСС
       *
       * Кабина 2
       * Type: LAMP
       * Name: PSS3
       */
      PSS3_138 = 138,

      /**
       * 
       *
       * Кабина 2
       * Type: LAMP
       * Name: LAMPPO
       */
      LAMPPO_139 = 139,

      /**
       * 
       *
       * Кабина 2
       * Type: SCREEN
       * Name: NUM1
       */
      NUM1_150 = 150,

      /**
       * 
       *
       * Кабина 2
       * Type: SCREEN
       * Name: NUM2
       */
      NUM2_151 = 151,

      /**
       * Давление масла до фильтра тонкой очистки
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM1
       */
      ARRM1_162 = 162,

      /**
       * Давление масла после фильтра тонкой очистки
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM2
       */
      ARRM2_163 = 163,

      /**
       * Давление топлива до фильтра тонкой очистки
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM3
       */
      ARRM3_164 = 164,

      /**
       * Давление топлива после фильтра тонкой очистки
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM4
       */
      ARRM4_165 = 165,

      /**
       * Наддув
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM5
       */
      ARRM5_166 = 166,

      /**
       * Давление в гидростатической системе
       *
       * Кабина 2
       * Type: ARROW
       * Name: ARRM6
       */
      ARRM6_167 = 167,

    };
  
  };

  /**
   * Кузов
   */
  namespace deck {
    
    /**
     * Переключатели.
     *   Кузов
     */
    enum class sw : unsigned short {
      
      /**
       * Шкаф
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: DOOR_F
       * Flags: CHECKDISTANCEONPRESS
       */
      DOOR_F_200 = 200,

      /**
       * Переключение кабины
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: RC1
       * Flags: CHECKDISTANCEONPRESS
       */
      RC1_201 = 201,

      /**
       * Переключение кабины
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: RC2
       * Flags: CHECKDISTANCEONPRESS
       */
      RC2_202 = 202,

      /**
       * Питание топливного насоса
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM6
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM6_203 = 203,

      /**
       * Отключение 1 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM1
       * Flags: CHECKDISTANCEONPRESS
       */
      OM1_204 = 204,

      /**
       * Отключение 2 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM2
       * Flags: CHECKDISTANCEONPRESS
       */
      OM2_205 = 205,

      /**
       * Отключение 3 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM3
       * Flags: CHECKDISTANCEONPRESS
       */
      OM3_206 = 206,

      /**
       * Отключение 4 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM4
       * Flags: CHECKDISTANCEONPRESS
       */
      OM4_207 = 207,

      /**
       * Отключение 5 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM5
       * Flags: CHECKDISTANCEONPRESS
       */
      OM5_208 = 208,

      /**
       * Отключение 6 ТЭД
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: OM6
       * Flags: CHECKDISTANCEONPRESS
       */
      OM6_209 = 209,

      /**
       * АЗВ освещение дизельного
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM1
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM1_210 = 210,

      /**
       * АЗВ резервное освещение дизельного
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM2
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM2_211 = 211,

      /**
       * АЗВ освещение подкузовное
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM3
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM3_212 = 212,

      /**
       * АЗВ освещение ВВК
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM4
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM4_213 = 213,

      /**
       * АЗВ питание розеток
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM5
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM5_214 = 214,

      /**
       * АЗВ питание автостопа
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM6
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM6_215 = 215,

      /**
       * АЗВ питание автостопа
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM7
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM7_216 = 216,

      /**
       * АЗВ пожарная сигнализация
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM8
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM8_217 = 217,

      /**
       * АЗВ буферные фонари
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM9
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM9_218 = 218,

      /**
       * АЗВ питание розеток
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM10
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM10_219 = 219,

      /**
       * АЗВ вспомогательные цепи
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM11
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM11_220 = 220,

      /**
       * АЗВ компрессор
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM12
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM12_221 = 221,

      /**
       * АЗВ топливный насос
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM13
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM13_222 = 222,

      /**
       * АЗВ калорифер
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: AZVM14
       * Flags: CHECKDISTANCEONPRESS
       */
      AZVM14_223 = 223,

      /**
       * Резервное питание насоса
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM1
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM1_224 = 224,

      /**
       * Автостоп
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM2
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM2_225 = 225,

      /**
       * Шунтировка
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM3
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM3_226 = 226,

      /**
       * Электродинамический тормоз
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM4
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM4_227 = 227,

      /**
       * Ручная прокачка масла
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: TBM5
       * Flags: CHECKDISTANCEONPRESS
       */
      TBM5_228 = 228,

      /**
       * Батарея 1
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: RUB_AB1
       * Flags: CHECKDISTANCEONPRESS
       */
      RUB_AB1_229 = 229,

      /**
       * Батарея 2
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: RUB_AB2
       * Flags: CHECKDISTANCEONPRESS
       */
      RUB_AB2_230 = 230,

      /**
       * UNNAMED
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: KR1
       * Flags: CHECKDISTANCEONPRESS
       */
      KR1_233 = 233,

      /**
       * UNNAMED
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: KR2
       * Flags: CHECKDISTANCEONPRESS
       */
      KR2_234 = 234,

      /**
       * UNNAMED
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: KR3
       * Flags: CHECKDISTANCEONPRESS
       */
      KR3_235 = 235,

      /**
       * UNNAMED
       *   Кузов
       * Type: Выключатель/тумблер
       * Name: KR4
       * Flags: CHECKDISTANCEONPRESS
       */
      KR4_236 = 236,

    };
  
    /**
     * Указатели.
     * Кузов
     */
    enum class disp : unsigned short {
      
    };
  
  };
};

  