package Controller;

import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.support.igd.PortMappingListener;
import org.teleal.cling.support.model.PortMapping;

public class UPNP {
	private static UpnpService upnpService;
	public static void UPNPres(int port, String ip, String name){
		PortMapping desiredMapping =
		        new PortMapping(
		                port,
		                ip,
		                PortMapping.Protocol.TCP,
		                name
		        );
		PortMapping desiredMapping1 =
				new PortMapping(
		                port,
		                ip,
		                PortMapping.Protocol.UDP,
		                name
		        );

		upnpService =
		        new UpnpServiceImpl(
		                new PortMappingListener(desiredMapping), new PortMappingListener(desiredMapping1)
		        );

		upnpService.getControlPoint().search();
	}
	public static void UPNPshut(){
		upnpService.shutdown();
	}
}
