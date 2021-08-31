using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using FIT5032_HotelBooking.Models;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace FIT5032_HotelBooking.Utils
{
    public class EmailSender
    {
        // Please use your API KEY here.
        private const String API_KEY = "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";

        public void Send(SendEmailViewModel model, String fullPath)
        {
            string fileName = Path.GetFileName(model.AttachedFile.FileName);

            var bytes = System.IO.File.ReadAllBytes(fullPath);
            var file = Convert.ToBase64String(bytes);
            var client = new SendGridClient(API_KEY);

            var from = new EmailAddress("229077035@qq.com", "FIT5032 Example Email User");
            //var to = new EmailAddress(toEmail, "");

            var to_emails = new List<EmailAddress>
            {
                new EmailAddress(model.ToEmail, "Example User1")
            };


            var msg = new SendGridMessage();
            msg.SetFrom("229077035@qq.com", "Hao Wang");
            msg.PlainTextContent = model.Contents;
            msg.Subject = model.Subject;
            msg.AddAttachment(model.AttachedFile.FileName, file);
            msg.AddTos(to_emails);
            var response = client.SendEmailAsync(msg);
        }
    }
}