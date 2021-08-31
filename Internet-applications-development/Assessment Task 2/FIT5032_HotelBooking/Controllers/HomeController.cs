using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using FIT5032_HotelBooking.Models;
using FIT5032_HotelBooking.Utils;

namespace FIT5032_HotelBooking.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        [Authorize(Roles = "Staff")]
        public ActionResult Send_Email()
        {
            return View(new SendEmailViewModel());
        }

        [Authorize(Roles = "Staff")]
        [HttpPost]
        public ActionResult Send_Email(SendEmailViewModel model)
        {
            try
            {
                string path = Server.MapPath("~/Utils/Uploads");
                string fileName = Path.GetFileName(model.AttachedFile.FileName);
                string fullPath = Path.Combine(path, fileName);
                model.AttachedFile.SaveAs(fullPath);

                EmailSender es = new EmailSender();
                es.Send(model, fullPath);

                ViewBag.Result = "Email has been send.";


                ModelState.Clear();

                return View(new SendEmailViewModel());
            }
            catch (Exception e)
            {
                return View();
            }


            return View();
        }
    }
}