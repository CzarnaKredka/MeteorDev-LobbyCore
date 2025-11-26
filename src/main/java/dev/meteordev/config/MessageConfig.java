package dev.meteordev.config;

import dev.meteordev.config.message.Message;
import dev.meteordev.config.message.MessageType;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.platform.core.annotation.Configuration;

@Configuration(path = "messages.yml")
public class MessageConfig extends OkaeriConfig {

    @Comment()
    @Comment("Wiadomości przy zmienieniu trybu gry /gm:")
    public Message changeGamemode = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fUstawiono tryb gry na &#FF7575{GAMEMODE} &8∙");
    public Message changeGamemodeOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fUstawiono tryb gry na &#FF7575{GAMEMODE} &fgraczowi &#FF4848{TARGET} &8∙");

    @Comment()
    @Comment("Wiadomości przy uleczeniu się /heal:")
    public Message onHealOwn = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie uleczono! &8∙");
    public Message onHealOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie uleczono &fgracza &#FF7575{TARGET} &8∙");

    @Comment()
    @Comment("Wiadomości przy najedzeniu się /feed:")
    public Message onFeedOwn = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie najedzono! &8∙");
    public Message onFeedOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie najedzono &fgracza &#FF7575{TARGET} &8∙");

    @Comment
    @Comment("Wiadomość przy użyciu /tphere")
    public Message onTpherePlayer = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie przywołano &cgracza! &8∙");

    @Comment
    @Comment("Wiadomość przy zmianie szybkości latania /flyspeed")
    public Message onFlySpeedUse = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie zmieniono tryb latania na &c{SPEED} &8∙");
    public Message onFlySpeedUsePlayer = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie zmieniono tryb latania na &c{SPEED} &fgraczowi &c{TARGET} &8∙");

    @Comment
    @Comment("Wiadomość przy zmianie szybkości chodzenia /speed")
    public Message onSpeedUse = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie zmieniono szybkość chodzenia na &c{SPEED} &8∙");
    public Message onSpeedUsePlayer = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie zmieniono szybkość chodzenia na &c{SPEED} &fgraczowi &c{TARGET} &8∙");

    @Comment()
    @Comment("Wiadomości przy włączeniu/wyłączeniu fly:")
    public Message onFlyToggle = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &awłączono &ftryb latania &8∙");
    public Message offFlyToggle = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &cwyłączono &ftryb latania &8∙");
    public Message onFlyToggleOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &awłączono &ftryb latania graczowi &#FF7575{TARGET} &8∙");
    public Message offFlyToggleOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &cwyłączono &ftryb latania graczowi &#FF7575{TARGET} &8∙");

    @Comment()
    @Comment("Wiadomości przy wejściu/wyjściu gracza na serwer:")
    public Message onJoinNewPlayer = new Message(MessageType.CHAT, "&8[&4⭐&8] &fGracz &c{TARGET} &fwbił pierwszy raz na &#FF7575serwer.");
    public Message onJoinPlayer = new Message(MessageType.CHAT, "&8[&#FF7575⚡&8] &fGracz &c{TARGET} &fwbił na &#FF7575serwer.");
    public Message onQuitPlayer = new Message(MessageType.CHAT, "&8[&#FF7575❌&8] &fGracz &c{TARGET} &fwyszedł z &#FF7575serwera.");

    @Comment()
    @Comment("Wiadomości przy włączeniu/wyłączeniu gammy:")
    public Message changeOnGamma = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &awłączono &fgammę! &8∙");
    public Message changeOffGamma = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &cwyłączono &fgammę! &8∙");
    public Message changeOnGammaOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &awłączono &fgamme dla &#FF7575{TARGET} &8∙");
    public Message changeOffGammaOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie &cwyłączono &fgamme dla &#FF7575{TARGET} &8∙");

    @Comment
    @Comment("Wiadomość po wpisaniu /adminchat <message>:")
    public Message onAdminChatMessage = new Message(MessageType.CHAT, "&8[&4❄&8] &cAdministrator &4{PLAYER} &cnapisał: &#FF0000{MESSAGE}");

    @Comment
    @Comment("Wiadomość po wpisaniu /alert <message>")
    public Message onAlertSend = new Message(MessageType.CHAT, "&2✔ &8| &aPomyślnie wysłano &2ogłoszenie &ado wszystkich!");
    public Message onAlertSendPlayers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&f{MESSAGE}");

    @Comment()
    @Comment("Wiadomości przy wyczyszczeniu ekwipunku /clear:")
    public Message onClearOwn = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie wyczyszczono &#FF7575ekwipunek! &8∙");
    public Message onClearOthers = new Message(MessageType.TITLE_SUBTITLE, "&#FF4848&lᴍ&#FF5353&lᴇ&#FF5F5F&lᴛ&#FF6A6A&lᴇ&#FF7575&lᴏ&#FF6A6A&lʀ&#FF5F5F&lᴅ&#FF5353&lᴇ&#FF4848&lᴠ%NOWA_LINIA%&8∙ &fPomyślnie wyczyszczono ekwipunek &fgracza &#FF7575{TARGET} &8∙");

    @Comment()
    @Comment("Wiadomość przy użyciu /chat:")
    public Message onDisableChat = new Message(MessageType.CHAT, "&r\n     &#FF4848&lᴄ&#FF4E4E&lʜ&#FF5353&lᴀ&#FF5959&lᴛ &#FF6464&lᴢ&#FF6A6A&lᴀ&#FF6F6F&lꜱ&#FF7575&lᴛ&#FF6F6F&lᴏ&#FF6868&lᴘ&#FF6262&lᴏ&#FF5B5B&lᴡ&#FF5555&lᴀ&#FF4E4E&lɴ&#FF4848&lʏ\n&r\n&8&l⏵ &fAdministrator o nicku &c{PLAYER}\n&8&l⏵ &fPomyślnie &#FF7575wyłączył &fchat!\n&r");
    public Message onClearChat = new Message(MessageType.CHAT, "&r\n     &#FF4848&lᴄ&#FF4E4E&lʜ&#FF5353&lᴀ&#FF5959&lᴛ &#FF6464&lᴡ&#FF6A6A&lʏ&#FF6F6F&lᴄ&#FF7575&lᴢ&#FF6F6F&lʏ&#FF6A6A&lꜱ&#FF6464&lᴢ&#FF5F5F&lᴄ&#FF5959&lᴢ&#FF5353&lᴏ&#FF4E4E&lɴ&#FF4848&lʏ\n&r\n&8&l⏵ &fAdministrator o nicku &c{PLAYER}\n&8&l⏵ &fPomyślnie &#FF7575wyczyścił &fchat!\n&r");
    public Message onEnableChat = new Message(MessageType.CHAT, "&r\n     &#FF4848&lᴄ&#FF4E4E&lʜ&#FF5555&lᴀ&#FF5B5B&lᴛ &#FF6868&lᴡ&#FF6F6F&lᴢ&#FF7575&lɴ&#FF6E6E&lᴏ&#FF6666&lᴡ&#FF5F5F&lɪ&#FF5757&lᴏ&#FF5050&lɴ&#FF4848&lʏ\n&r\n&8&l⏵ &fAdministrator o nicku &c{PLAYER}\n&8&l⏵ &fPomyślnie &#FF7575włączył &fchat!\n&r");

}
