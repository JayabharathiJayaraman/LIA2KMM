import UIKit
import MapKit

class MapScreenViewController: UIViewController, CLLocationManagerDelegate {
    
    
    @IBOutlet weak var mapView: MKMapView!
    

    let locationManger = CLLocationManager()

       

       override func viewDidLoad() {
           super.viewDidLoad()
           checkLocationServices()
           
         
       }
    
    override var shouldAutorotate: Bool{
         return false
     }
     
     override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
         return .portrait
     }
    
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {

        guard !annotation.isKind(of: MKUserLocation.self) else { return nil }


        return MKAnnotationView()
    }
       
       func setupLocationManger() {
           locationManger.delegate = self
           locationManger.desiredAccuracy = kCLLocationAccuracyBest
       }
       
       func checkLocationServices() {
           if CLLocationManager.locationServicesEnabled(){
               setupLocationManger()
               checkLocationAuthorization()
           } else {
               
           }
       }
       
       func checkLocationAuthorization() {
           switch CLLocationManager.authorizationStatus() {
           case .authorizedWhenInUse:
               mapView.showsUserLocation = true
               break
           case .denied:
               break
           case .notDetermined:
               locationManger.requestWhenInUseAuthorization()
           case .restricted:
               break
           case .authorizedAlways:
               break
           }
       }


   }
