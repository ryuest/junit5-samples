package com.whgtf.sportsbook.model;

import java.util.LinkedHashMap;

public class CompetitionIds {

    private static final String SPORT_SOFTBALL = "softball";
    private static final String SPORT_FOOTBALL = "football";
    private static final String SPORT_SPECIAL = "special";
    private static final String SPORT_CANOEING = "canoeing";
    private static final String SPORT_BASKETBALL = "basketball";
    private static final String SPORT_BASEBALL = "baseball";
    private static final String SPORT_RACING = "horseracing";
    private static final String SPORT_GREYHOUNDS = "greyhounds";
    private static final String SPORT_BOXING = "boxing";
    private static final String SPORT_TENNIS = "tennis";
    private static final String SPORT_DARTS = "darts";


    private CompetitionIds() {
        // utility class
    }

    private static final LinkedHashMap<String, Integer> competitionsMap;
    static {
        competitionsMap = new LinkedHashMap<String, Integer>();

        competitionsMap.put("English Classics", 749); // antepost Horse Racing
        competitionsMap.put("Royal Ascot", 764); // antepost Horse Racing
        competitionsMap.put("Grand National", 766); // antepost Horse Racing
        competitionsMap.put("Cheltenham Festival", 18); // antepost Horse Racing
        competitionsMap.put("antepost", 18); // antepost Horse Racing

        competitionsMap.put("Top Jockey", 540); // specials Horse Racing
        competitionsMap.put("specials", 540); // specials Horse Racing
        competitionsMap.put("Grand National Specials", 1163); // specials Horse Racing
        competitionsMap.put("Cheltenham Festival Specials", 1167); // specials Horse Racing
        competitionsMap.put("Jockey Offers", 1014); // specials Horse Racing

        competitionsMap.put("Antepost Races", 809); // antepost Greyhounds
        competitionsMap.put("Betfred Gymcrack", 11528); // antepost Greyhounds

        competitionsMap.put("Hotbox", 6775); // specials Greyhounds
        competitionsMap.put("Special Distance Betting", 10449); // specials Greyhounds
        competitionsMap.put("Betting Without", 2274); // specials Greyhounds
        competitionsMap.put("Specials", 778); // specials Greyhounds


        // Greyhounds Live
        competitionsMap.put("Oxford", 42); // Greyhounds
        competitionsMap.put("Brushwood",1);
        competitionsMap.put("Belle Vue",17);
        competitionsMap.put("Crayford",25);
        competitionsMap.put("Hall Green",80);
        competitionsMap.put("Harlow",695);
        competitionsMap.put("Hove",41);
        competitionsMap.put("Henlow",697);
        competitionsMap.put("Kinsley",43);
        competitionsMap.put("Monmore",57);

        /////////////////////Horse Racing UK//////////////////////////////////////////////////

        competitionsMap.put("Doncaster", 254);
        competitionsMap.put("Bangor", 251);
        competitionsMap.put("Aintree", 9);
        competitionsMap.put("Ascot", 120);
        competitionsMap.put("Ayr", 8);
        competitionsMap.put("Beverley", 78);
        competitionsMap.put("Carlisle", 206);
        competitionsMap.put("Cartmel", 252);
        competitionsMap.put("Catterick", 185);
        competitionsMap.put("Cheltenham", 19);
        competitionsMap.put("Chester", 22);
        competitionsMap.put("Epsom Downs", 1533);
        competitionsMap.put("Exeter", 190);
        competitionsMap.put("Goodwood", 20);
        competitionsMap.put("Hamilton",128 );
        competitionsMap.put("Haydock ",258);
        competitionsMap.put("Huntingdon", 119);
        competitionsMap.put("Kelso", 171);
        competitionsMap.put("Kempton",36);
        competitionsMap.put("Ludlow", 236);
        competitionsMap.put("Market Rasen", 169);
        competitionsMap.put("Musselburgh", 121);
        competitionsMap.put("Newbury", 219);
        competitionsMap.put("Newmarket", 101);
        competitionsMap.put("Nottingham", 137);
        competitionsMap.put("Pontefract", 111);
        competitionsMap.put("Perth", 110);
        competitionsMap.put("Redcar", 52);
        competitionsMap.put("Salisbury", 148);
        competitionsMap.put("Sandown Park", 42);
        competitionsMap.put("Thirsk", 264);
        competitionsMap.put("Warwick", 246);
        competitionsMap.put("Wetherby", 224);
        competitionsMap.put("Wincanton", 197);
        competitionsMap.put("York", 21);

        /////////////////////  ATR broadcasts racing tracks in the UK//////////////////////////////////

        competitionsMap.put("Bath",10 );
        competitionsMap.put("Brighton",102 );
        // competitionsMap.put("Chelmsford", );
        competitionsMap.put("Chepstow",253 );
        competitionsMap.put("Fakenham",256 );
        competitionsMap.put("FFos Las",1973 );
        competitionsMap.put("Fontwell",112 );
        competitionsMap.put("Hexham",160 );
        competitionsMap.put("Leicester",184 );
        competitionsMap.put("Lingfield",66 );
        competitionsMap.put("Newcastle", 147);
        competitionsMap.put("Newton Abbot",259 );
        competitionsMap.put("Plumpton",260 );
        competitionsMap.put("Ripon",261 );
        competitionsMap.put("Sedgefield",138 );
        competitionsMap.put("Southwell",235 );
        competitionsMap.put("Stratford",37 );
        competitionsMap.put("Taunton", 263);
        competitionsMap.put("Towcester",191 );
        competitionsMap.put("Uttoxeter",170 );
        competitionsMap.put("Windsor",178 );
        competitionsMap.put("Wolverhampton",1369 );
        competitionsMap.put("Worcester",196 );
        competitionsMap.put("Yarmouth",65);

        /////////////////////  ATR broadcasts racing tracks Irish racecourses//////////////////////////////////

        competitionsMap.put("Ballinrobe",139);
        competitionsMap.put("Bellewstown",550);
        competitionsMap.put("Clonmel",551);
        competitionsMap.put("Cork",39);
        competitionsMap.put("Curragh",103);
        competitionsMap.put("Down Royal",552);
        competitionsMap.put("Downpatrick",149);
        competitionsMap.put("Dundalk",553);
        competitionsMap.put("Fairyhouse",164);
        competitionsMap.put("Galway",38);
        //competitionsMap.put("Gowran", ); or  Gowran Park ? 207
        competitionsMap.put("Kilbeggan",555 );
        competitionsMap.put("Killarney, ",556);
        competitionsMap.put("Laytown",557);
        competitionsMap.put("Leopardstown",23);
        competitionsMap.put("Limerick",210 );
        competitionsMap.put("Listowel",104);
        competitionsMap.put("Naas",561);
        competitionsMap.put("Navan",562);
        competitionsMap.put("Punchestown",565);
        competitionsMap.put("Roscommon",183);
        competitionsMap.put("Sligo",79);
        competitionsMap.put("Thurles",567);
        competitionsMap.put("Tipperary",172);
        competitionsMap.put("Tralee",192 );
        competitionsMap.put("Tramore",237);
        competitionsMap.put("Wexford",568);

        competitionsMap.put("Scottish U21 Matches", 13235);
        competitionsMap.put("World Championships", 803);

    }

    private static final LinkedHashMap<String, Integer> softballCompetitionsMap;
    static {
        softballCompetitionsMap = new LinkedHashMap<String, Integer>();
        softballCompetitionsMap.put("World Championships", 803);
    }

    private static final LinkedHashMap<String, Integer> canoeingCompetitionsMap;
    static {
        canoeingCompetitionsMap = new LinkedHashMap<String, Integer>();
        canoeingCompetitionsMap.put("World Championships", 791);
    }

    private static final LinkedHashMap<String, Integer> tennisCompetitionsMap;
    static {
        tennisCompetitionsMap = new LinkedHashMap<String, Integer>();
        tennisCompetitionsMap.put("Men's French Open", 18475);
    }

    private static final LinkedHashMap<String, Integer> dartsCompetitionsMap;
    static {
        dartsCompetitionsMap = new LinkedHashMap<String, Integer>();
        dartsCompetitionsMap.put("Premier League", 286);
        dartsCompetitionsMap.put("The Masters", 17743);
    }

    private static final LinkedHashMap<String, Integer> basketballCompetitionsMap;
    static {
        basketballCompetitionsMap = new LinkedHashMap<String, Integer>();
        basketballCompetitionsMap.put("Specials", 772);
        basketballCompetitionsMap.put("NBA", 266);
    }


    private static final LinkedHashMap<String, Integer> baseballCompetitionsMap;
    static {
        baseballCompetitionsMap = new LinkedHashMap<String, Integer>();
        baseballCompetitionsMap.put("Baseball Specials", 9950);
        baseballCompetitionsMap.put("MLB", 226);
    }

    private static final LinkedHashMap<String, Integer> boxingCompetitionsMap;
    static {
        boxingCompetitionsMap = new LinkedHashMap<String, Integer>();
        boxingCompetitionsMap.put("Heavyweight", 250);
    }

    private static final LinkedHashMap<String, Integer> footballCompetitionsMap;
    static {
        footballCompetitionsMap = new LinkedHashMap<String, Integer>();
        footballCompetitionsMap.put("Scottish U21 Matches", 13235);
        footballCompetitionsMap.put("English Premier League", 295);
        footballCompetitionsMap.put("UEFA Champions League", 344);
        footballCompetitionsMap.put("Spanish La Liga Primera", 338);
        footballCompetitionsMap.put("German Bundesliga", 315);
        footballCompetitionsMap.put("Italian Serie A", 321);
        footballCompetitionsMap.put("Portuguese Primeira Liga", 331);
        footballCompetitionsMap.put("Spanish Segunda", 339);
        footballCompetitionsMap.put("Austrian KFV Cup",26003 );
        footballCompetitionsMap.put("Belarus Premier League",3605 );
        footballCompetitionsMap.put("Czech Krajsky Prebor", 20276);
        footballCompetitionsMap.put("English FA Cup", 129);
        footballCompetitionsMap.put("English League 1", 293);
        footballCompetitionsMap.put("English League 2", 294);
        footballCompetitionsMap.put("Scottish Premiership", 297);
        footballCompetitionsMap.put("Scottish Championship", 2);
        footballCompetitionsMap.put("English League Cup", 25740);
        footballCompetitionsMap.put("Russian Premier League", 334);
        footballCompetitionsMap.put("Slovakian Slovensky Pohar",13122 );
        footballCompetitionsMap.put("Serbian Super Liga Women", 22738);
    }

    public static Integer getCompetitionId(String competitionName) {
        Integer competitionId = competitionsMap.get(competitionName);

        if(competitionId == null)
            throw new IllegalArgumentException("No competition available with name: " + competitionName);
        else
            return competitionId;
    }

    public static Integer getFirstCompetitionId() {
        return competitionsMap.values().iterator().next();
    }

    public static Integer getFirstCompetitionId(String sport) {
        if(sport.equalsIgnoreCase(SPORT_FOOTBALL))
            return footballCompetitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_SOFTBALL))
            return softballCompetitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_CANOEING))
            return canoeingCompetitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_BASKETBALL))
            return basketballCompetitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_BASEBALL))
            return baseballCompetitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_RACING))
            return competitionsMap.values().iterator().next();
        else if(sport.equalsIgnoreCase(SPORT_GREYHOUNDS))
            return competitionsMap.values().iterator().next();
        else
            throw new IllegalArgumentException("No sport available with name: " + sport);
    }

    public static Integer getCompetitionId(String sport, String competitionName) {
        Integer competitionId = null;
        if(sport.equalsIgnoreCase(SPORT_FOOTBALL) || sport.equalsIgnoreCase(SPORT_SPECIAL))
            competitionId =  footballCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_SOFTBALL))
            competitionId = softballCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_CANOEING))
            competitionId = canoeingCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_RACING))
            competitionId = competitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_GREYHOUNDS))
            competitionId = competitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_BASKETBALL))
            competitionId = basketballCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_BOXING))
            competitionId = boxingCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_BASEBALL))
            competitionId = baseballCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_TENNIS))
            competitionId = tennisCompetitionsMap.get(competitionName);
        else if(sport.equalsIgnoreCase(SPORT_DARTS))
            competitionId = dartsCompetitionsMap.get(competitionName);
        else
            throw new IllegalArgumentException("No sport available with name: " + sport);

        if(competitionId == null)
            throw new IllegalArgumentException("No competition available with name: " + competitionName);
        else
            return competitionId;
    }

    public static String getCompetitionName(Integer competitionId) {
        if (competitionsMap.containsValue(competitionId))
            for (final String entry : competitionsMap.keySet())
                if (competitionsMap.get(entry).equals(competitionId))
                    return entry;

        throw new IllegalArgumentException("No competition available with id: " + competitionId);
    }


}
