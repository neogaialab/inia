package inia.comp;

public class PlayerlistHeaderFooterWrapper {
    
    private ChatBaseComponentWrapper headerComponentWrapper, footerComponentWrapper;

    public PlayerlistHeaderFooterWrapper(ChatBaseComponentWrapper headerComponentWrapper, ChatBaseComponentWrapper footerComponentWrapper) {
        this.setHeaderComponentWrapper(headerComponentWrapper);
        this.setFooterComponentWrapper(footerComponentWrapper);
    }

    public ChatBaseComponentWrapper getHeaderComponentWrapper() {
        return this.headerComponentWrapper;
    }

    public void setHeaderComponentWrapper(ChatBaseComponentWrapper headerComponentWrapper) {
        this.headerComponentWrapper = headerComponentWrapper;
    }

    public ChatBaseComponentWrapper getFooterComponentWrapper() {
        return this.footerComponentWrapper;
    }

    public void setFooterComponentWrapper(ChatBaseComponentWrapper footerComponentWrapper) {
        this.footerComponentWrapper = footerComponentWrapper;
    }
}
