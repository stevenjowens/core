/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platypus.crw.udp;

import com.platypus.crw.udp.UdpServer.Request;
import java.net.DatagramPacket;
import com.platypus.crw.udp.UdpServer.QueuedResponse;
import com.platypus.crw.udp.UdpServer.RequestHandler;
import com.platypus.crw.udp.UdpServer.Response;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Prasanna Velagapudi <psigen@gmail.com>
 */
public class UdpServerTest {

    public UdpServerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serialization classes Request, Response, and QueuedResponse
     */
    @Test
    public void testRequestResponse() throws IOException {
        Random rnd = new Random();
        Long ticket = rnd.nextLong();
        String command = UUID.randomUUID().toString();
        int payload1 = rnd.nextInt();
        double payload2 = rnd.nextDouble();
        SocketAddress dest = new InetSocketAddress("127.0.0.1", 60003);

        Response resp = new Response(ticket, dest);
        assertEquals((long)ticket, resp.ticket);
        assertEquals(dest, resp.destination);
        resp.stream.writeUTF(command);
        resp.stream.writeInt(payload1);
        resp.stream.writeDouble(payload2);

        QueuedResponse qr = new QueuedResponse(resp, UdpConstants.INITIAL_RETRY_RATE_NS);
        assertEquals((long)ticket, qr.ticket);
        assertEquals(dest, qr.destination);

        DatagramPacket packet = qr.toPacket();
        assertEquals(dest, packet.getSocketAddress());

        Request req = new Request(packet);
        assertEquals(dest, req.source);
        assertEquals((long)ticket, req.ticket);
        assertEquals(command, req.stream.readUTF());
        assertEquals(payload1, req.stream.readInt());
        assertEquals(payload2, req.stream.readDouble(), 1e-7);
    }

    /**
     * Test of start method, of class UdpServer.
     */
    @Test
    public void testStart() {
        UdpServer instance = new UdpServer();
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class UdpServer.
     */
    @Test
    public void testStop() {
        UdpServer instance = new UdpServer();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSocketAddress method, of class UdpServer.
     */
    @Test
    public void testGetSocketAddress() {
        UdpServer instance = new UdpServer();
        SocketAddress expResult = null;
        SocketAddress result = instance.getSocketAddress();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setHandler method, of class UdpServer.
     */
    @Test
    public void testSetHandler() {
        RequestHandler handler = null;
        UdpServer instance = new UdpServer();
        instance.setHandler(handler);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of respond method, of class UdpServer.
     */
    @Test
    public void testRespond() {
        Response response = null;
        UdpServer instance = new UdpServer();
        instance.respond(response);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of bcast method, of class UdpServer.
     */
    @Test
    public void testBcast() {
        Response response = null;
        List<SocketAddress> destinations = null;
        UdpServer instance = new UdpServer();
        instance.bcast(response, destinations);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class UdpServer.
     */
    @Test
    public void testSend() {
        Response response = null;
        UdpServer instance = new UdpServer();
        instance.send(response);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of acknowledge method, of class UdpServer.
     */
    @Test
    public void testAcknowledge() {
        long ticket = 0L;
        UdpServer instance = new UdpServer();
        instance.acknowledge(ticket);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
