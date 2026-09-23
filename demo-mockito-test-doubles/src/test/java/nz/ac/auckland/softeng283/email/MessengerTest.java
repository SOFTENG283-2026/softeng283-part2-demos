package nz.ac.auckland.softeng283.email;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MessengerTest {

  @Test
  public void sendMessage_sendMessage_success() {
    // ARRANGE
    MailServer server = Mockito.mock(MailServer.class);
    TemplateEngine templateEngine = Mockito.mock(TemplateEngine.class);
    Client client = Mockito.mock(Client.class);
    Template template = Mockito.mock(Template.class);
    Messenger msg = new Messenger(server, templateEngine);
    // STUB
    Mockito.when(templateEngine.prepareMessage(template, client)).thenReturn("Hello");
    Mockito.when(client.getEmail()).thenReturn("v.terragni@auckland.ac.nz");
    // ACT
    msg.sendMessage(client, template);
    // ASSERT
    Mockito.verify(server).send("v.terragni@auckland.ac.nz","Hello");
  }
}
